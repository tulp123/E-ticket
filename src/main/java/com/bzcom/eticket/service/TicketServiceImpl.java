package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.TicketDao;
import com.bzcom.eticket.model.Event;
import com.bzcom.eticket.model.Seat;
import com.bzcom.eticket.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    public Ticket save(Ticket ticket) {
        return ticketDao.save(ticket);
    }

    @Override
    public List<Ticket> saveAll(Ticket ticket) {
        List<Ticket> ticketList = new ArrayList<>();

        for (int i = 1; i <= ticket.getTotalTicket(); i++) {
            Ticket eticket = new Ticket();

            eticket.setSeat(ticket.getSeat());
            eticket.setIssueDate(ticket.getIssueDate());
            eticket.setPaymentMethod(ticket.getPaymentMethod());
//            eticket.setQrCode(ticket.getQrCode());
            eticket.setTicketStatus(ticket.isTicketStatus());
            eticket.setEventId(ticket.getEventId());
            eticket.setUserId(ticket.getUserId());

            Event eventTicket = eventService.findById(eticket.getEventId());
            Seat seatTicket = seatService.findById(eticket.getSeat().getId());
            eticket.setEvent(eventTicket);
            eticket.setSeat(seatTicket);

            // auto generate booking code
            String bookingCode = this.randomBookingCode();
            eticket.setBookingCode(bookingCode);

            String maxSerial = this.getMaxSerial(eventTicket.getId());
            String serial = "";

            // auto increment ticket serial
            if (maxSerial == null){
                serial = this.autoGenerateSerial(eventTicket);
            } else {
                serial = String.valueOf(Long.parseLong(maxSerial) + i);
            }
            eticket.setTicketSerial(serial);

            ticketList.add(eticket);
        }

        return ticketDao.saveAll(ticketList);
    }

    private String autoGenerateSerial(Event eventTicket) {
        StringBuilder sb = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
        sb.append(sdf.format(eventTicket.getGame().getMatchTime()));

        String eventId = String.format("%4s", eventTicket.getId()).replace(' ','0');
        sb.append(eventId);

        sb.append("0001");
        return sb.toString();
    }

    private String randomBookingCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
        int bookingCodeChar = 8;
        StringBuilder sb = new StringBuilder(bookingCodeChar);
        for(int i=0; i < bookingCodeChar; i++ ) {
            int index = (int) (chars.length() * Math.random());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    @Override
    public Ticket findByBookingCode(String bookingCode, Event event) {
        return ticketDao.findByBookingCode(bookingCode, event);
    }

    @Override
    public Ticket findByTicketSerial(String ticketSerial, Event event) {
        return ticketDao.findByTicketSerial(ticketSerial, event);
    }

    @Override
    public String getMaxSerial(int eventId) {
        return ticketDao.getMaxSerial(eventId);
    }
}
