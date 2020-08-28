package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Event;
import com.bzcom.eticket.model.Ticket;

import java.util.List;

public interface TicketDao {

    List<Ticket> findAll();

    Ticket findById(int id);

    Ticket save(Ticket ticket);

    List<Ticket> saveAll(List<Ticket> tickets);

    Ticket findByBookingCode(String bookingCode, Event event);

    Ticket findByTicketSerial(String ticketSerial, Event event);

    String getMaxSerial(int eventId);

}
