package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.TicketDao;
import com.bzcom.eticket.dto.AreaBookingDTO;
import com.bzcom.eticket.dto.BookingTicketDTO;
import com.bzcom.eticket.model.AreaPrice;
import com.bzcom.eticket.model.Event;
import com.bzcom.eticket.model.Seat;
import com.bzcom.eticket.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private EventService eventService;

    @Autowired
    private SeatService seatService;

    @Override
    public List<Ticket> findAll() {
        return ticketDao.findAll();
    }

    @Override
    public Ticket findById(int id) {
        return ticketDao.findById(id);
    }

    @Override
    public List<Ticket> findAllByIds(List<Integer> ids) {
        return ticketDao.findAllByIds(ids);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketDao.save(ticket);
    }

    @Override
    @Transactional
    public List<Ticket> saveAll(BookingTicketDTO bookingTicketDTO) {
        List<Ticket> ticketList = bookingTicketDTO.getTickets();
        List<Integer> ids = ticketList.stream().map(Ticket::getId).collect(Collectors.toList());
        ticketList = this.findAllByIds(ids);

        Event eventTicket = ticketList.get(0).getEvent();

        // auto generate booking code
        String bookingCode = "";
        do {
            bookingCode = this.randomBookingCode();
        } while (this.findByBookingCode(bookingCode, eventTicket).size() > 0);

        for (Ticket eTicket : ticketList) {
            eTicket.setUser(bookingTicketDTO.getUser());
            eTicket.setIssueDate(new Date());
            eTicket.setBookingCode(bookingCode);
            eTicket.setTicketStatus(false);
            eTicket.setBookingStatus(2);
        }

        return ticketDao.saveAll(ticketList);
    }

    private String autoGenerateSerial(Event eventTicket) {
        StringBuilder sb = new StringBuilder();

        String serial = String.valueOf(System.currentTimeMillis()).concat(String.valueOf(eventTicket.getId())).concat("001");

        int timeLength = serial.length();
        sb.append(serial.substring(timeLength - 12, timeLength));

//        String eventId = String.format("%4s", eventTicket.getId()).replace(' ', '0');
//        sb.append(eventId);

        return sb.toString();
    }

    private String randomBookingCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
        int bookingCodeChar = 8;
        StringBuilder sb = new StringBuilder(bookingCodeChar);
        for (int i = 0; i < bookingCodeChar; i++) {
            int index = (int) (chars.length() * Math.random());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    @Override
    public List<Ticket> createTicket(Event event, AreaPrice areaPrice, String serial) {
        List<Ticket> ticketList = new ArrayList<>();
        Integer minSeatId = seatService.getSeatIdMin(areaPrice.getId().getAreaId());

        for (int i = 0; i < areaPrice.getAreaTotalTicket(); i++) {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setTicketSerial(String.valueOf(Long.parseLong(serial) + i));
            ticket.setSeat(new Seat(minSeatId + i));
            ticket.setTicketStatus(false);
            ticketList.add(ticket);
        }

        return ticketDao.saveAll(ticketList);
    }

    @Override
    public List<Ticket> findByBookingCode(String bookingCode, Event event) {
        return ticketDao.findByBookingCode(bookingCode, event);
    }

    @Override
    public Ticket findByTicketSerial(String ticketSerial) {
        return ticketDao.findByTicketSerial(ticketSerial);
    }

    @Override
    public String getMaxSerial(int eventId) {
        return ticketDao.getMaxSerial(eventId);
    }

    @Override
    public List<Ticket> findTicketByBookingCodeAndUser_EmailAndUser_Phone(String bookingCode, String email, String phoneNumber) {
        return ticketDao.findTicketByBookingCodeAndUser_EmailAndUser_Phone(bookingCode , email , phoneNumber);
    }

    @Override
    public List<Ticket> getListTicketAvailable(int eventId, int areaId, int numberTicket) {
        return ticketDao.getListTicketAvailable(eventId, areaId, numberTicket);
    }

    @Override
    public void updateBookingStatus(int bookingStatus, List<Ticket> tickets) {
        ticketDao.updateBookingStatus(bookingStatus, tickets);
    }
}
