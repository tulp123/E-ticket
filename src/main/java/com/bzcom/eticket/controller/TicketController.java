package com.bzcom.eticket.controller;

import com.bzcom.eticket.dto.*;
import com.bzcom.eticket.model.AreaPrice;
import com.bzcom.eticket.model.Event;
import com.bzcom.eticket.model.Ticket;
import com.bzcom.eticket.service.EmailService;
import com.bzcom.eticket.service.EventService;
import com.bzcom.eticket.service.TicketService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/tickets"})
@CrossOrigin("*")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EmailService emailService;

    // Get All Ticket
    @GetMapping
    public List<Ticket> getAllTickets(){
        return ticketService.findAll();
    }

    // Get a Single Ticket
    @GetMapping("/{id}")
    public TicketDTO getTicketById(@PathVariable(value = "id") Integer id) {
        Ticket ticket = ticketService.findById(id);
        TicketDTO ticketDTO = new TicketDTO(ticket);
        return ticketDTO;
    }

    // find ticket by serial number
    @GetMapping("/serial")
    public Ticket getTicketBySerial(@RequestParam String serial) {
        return ticketService.findByTicketSerial(serial);
    }

    // find ticket by booking code
    @GetMapping("/bookingCode")
    public List<Ticket> getTicketByBookingCode(@RequestParam String bookingCode, @RequestParam int eventId) {
        return ticketService.findByBookingCode(bookingCode, eventService.findById(eventId));
    }

    @PostMapping("/availableTicket")
    public LockingResultDTO getListTicketAvailable(@RequestBody LockingTicketDTO lockingTicketDTO) {
        List<Ticket> ticketList = new ArrayList<>();

        for (AreaBookingDTO areaBookingDTO: lockingTicketDTO.getAreaBookingDTOList()) {
            if (areaBookingDTO.getTotalSeat() > 0) {
                List<Ticket> tickets = ticketService.getListTicketAvailable(lockingTicketDTO.getEventID(), areaBookingDTO.getAreaId(), areaBookingDTO.getTotalSeat());
                ticketList.addAll(tickets);
            }
        }

        System.out.println("--------- Locking ticket -----------");
        if (ticketList.size() == lockingTicketDTO.getTotalTicket()) {
            ticketService.updateBookingStatus(1, ticketList);
        }

        // run asynchronous process check lock ticket after 5 min
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(300000);
                List<Integer> ids = ticketList.stream().map(Ticket::getId).collect(Collectors.toList());
                List<Ticket> tickets = ticketService.findAllByIds(ids).stream().filter(ticket -> ticket.getBookingStatus() == 1).collect(Collectors.toList());
                if (tickets.size() == ticketList.size()) {
                    ticketService.updateBookingStatus(0, ticketList);
                }
                System.out.println("--------- Open ticket after 5 min ---------");
            }
        }).start();

        LockingResultDTO lockingResultDTO = new LockingResultDTO();
        lockingResultDTO.setTickets(ticketList);
        lockingResultDTO.setRemainAreaA(ticketList.stream().filter(ticket -> ticket.getSeat().getArea().getId()==1).count());
        lockingResultDTO.setRemainAreaB(ticketList.stream().filter(ticket -> ticket.getSeat().getArea().getId()==2).count());
        lockingResultDTO.setRemainAreaC(ticketList.stream().filter(ticket -> ticket.getSeat().getArea().getId()==3).count());
        lockingResultDTO.setRemainAreaD(ticketList.stream().filter(ticket -> ticket.getSeat().getArea().getId()==4).count());

        return lockingResultDTO;
    }

    // Create a new Ticket
    @PostMapping
    public List<TicketDTO> saveTicket(@RequestBody BookingTicketDTO bookingTicketDTO){

        List<Ticket> list = ticketService.saveAll(bookingTicketDTO);

//        Map<String, Object> model = new HashMap<>();
//        model.put("listTicket", list);
//        model.put("qrcode", "https://miro.medium.com/max/330/1*SDa8rAqN7JZ7cJfChoS5aw.png");
//        emailService.sendTicket(list, model);

        // run asynchronous process send booking code via e-mail
        new Thread(new Runnable() {
            @Override
            public void run() {
                emailService.sendBookingCode(list);
            }
        }).start();

//        CompletableFuture.runAsync(() -> {
//           emailService.sendBookingCode(list);
//        });

        return this.mapDataToTicketDTO(list);
    }

    private List<TicketDTO> mapDataToTicketDTO(List<Ticket> tickets) {
        List<TicketDTO> ticketDTOS = new ArrayList<>();
        for (Ticket ticket : tickets){
            TicketDTO ticketDTO = new TicketDTO(ticket);
            ticketDTOS.add(ticketDTO);
        }
        return ticketDTOS;
    }

    @PostMapping("auto-create")
    public List<Ticket> createTicket(@RequestBody Event event){
        String serial = this.autoGenerateSerial(event);

        for (int i = 0; i < event.getAreaPrices().size(); i++) {
            AreaPrice areaPrice = ((List<AreaPrice>) event.getAreaPrices()).get(i);
            ticketService.createTicket(event, areaPrice, serial);
            serial = String.valueOf(Long.parseLong(serial) + areaPrice.getAreaTotalTicket());
        }

        return null;
    }

    private String autoGenerateSerial(Event eventTicket) {
        StringBuilder sb = new StringBuilder();
        String serial = String.valueOf(System.currentTimeMillis()).concat(String.valueOf(eventTicket.getId())).concat("0001");
        int serialLength = serial.length();
        sb.append(serial.substring(serialLength - 12, serialLength));
        return sb.toString();
    }

    // Update a Ticket
    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable(value = "id") Integer id, @RequestBody Ticket ticket) {
        ticket.setId(id);
        Ticket ticketUpdate = this.ticketService.save(ticket);
        return ticketUpdate;
    }

    @GetMapping("/search")
    public List<TicketDTO> searchTicket(@RequestParam String bookingCode , @RequestParam String email , @RequestParam String phoneNum){
        List<Ticket> tickets =  ticketService.findTicketByBookingCodeAndUser_EmailAndUser_Phone(bookingCode , email ,phoneNum);
        return this.mapDataToTicketDTO(tickets);
    }

    @PutMapping("/changeStatus")
    public Ticket useTicket(@RequestParam String serial) {
        Ticket ticket = ticketService.findByTicketSerial(serial);
        if (ticket.isTicketStatus() == false) ticket.setTicketStatus(true);
        return ticketService.save(ticket);
    }
}
