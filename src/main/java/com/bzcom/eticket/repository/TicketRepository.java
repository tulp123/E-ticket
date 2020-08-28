package com.bzcom.eticket.repository;

import com.bzcom.eticket.model.Event;
import com.bzcom.eticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Ticket findAllByBookingCodeAndEvent(String bookingCode, Event event);

    Ticket findAllByTicketSerialAndEvent(String ticketSerial, Event event);
}
