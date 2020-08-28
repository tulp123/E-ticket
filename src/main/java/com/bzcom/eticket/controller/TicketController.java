package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.Ticket;
import com.bzcom.eticket.service.EmailService;
import com.bzcom.eticket.service.EventService;
import com.bzcom.eticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping({"/tickets"})
@CrossOrigin("http://localhost:3000")
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
    public Ticket getTicketById(@PathVariable(value = "id") Integer id) {
        return ticketService.findById(id);
    }

    // find ticket by serial number
    @GetMapping("/serial")
    public Ticket getTicketBySerial(@RequestParam String serial, @RequestParam int eventId) {
        return ticketService.findByTicketSerial(serial, eventService.findById(eventId));
    }

    // find ticket by booking code
    @GetMapping("/bookingCode")
    public Ticket getTicketByBookingCode(@RequestParam String bookingCode, @RequestParam int eventId) {
        return ticketService.findByBookingCode(bookingCode, eventService.findById(eventId));
    }

    // Create a new Ticket
    @PostMapping
    public List<Ticket> saveTicket(@RequestBody Ticket ticket){

        List<Ticket> list = ticketService.saveAll(ticket);

        Map<String, Object> model = new HashMap<>();
        model.put("listTicket", list);
        model.put("qrcode", "https://miro.medium.com/max/330/1*SDa8rAqN7JZ7cJfChoS5aw.png");
        emailService.sendTicket(list, model);

        return list;
    }

    // Update a Ticket
    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable(value = "id") Integer id, @RequestBody Ticket ticket) {
        ticket.setId(id);
        Ticket ticketUpdate = this.ticketService.save(ticket);
        return ticketUpdate;
    }

}
