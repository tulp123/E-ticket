package com.bzcom.eticket.service;

import com.bzcom.eticket.dto.BookingTicketDTO;
import com.bzcom.eticket.model.AreaPrice;
import com.bzcom.eticket.model.Event;
import com.bzcom.eticket.model.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> findAll();

    Ticket findById(int id);

    List<Ticket> findAllByIds(List<Integer> ids);

    Ticket save(Ticket ticket);

    List<Ticket> saveAll(BookingTicketDTO bookingTicketDTO);

    List<Ticket> createTicket(Event event, AreaPrice areaPrice, String serial);

    List<Ticket> findByBookingCode(String bookingCode, Event event);

    Ticket findByTicketSerial(String ticketSerial);

    String getMaxSerial(int eventId);

    List<Ticket> findTicketByBookingCodeAndUser_EmailAndUser_Phone(String bookingCode , String email ,String phoneNumber);

    List<Ticket> getListTicketAvailable(int eventId, int areaId, int numberTicket);

    void updateBookingStatus(int bookingStatus, List<Ticket> tickets);
}
