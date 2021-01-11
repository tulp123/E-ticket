package com.bzcom.eticket.repository;

import com.bzcom.eticket.model.Event;
import com.bzcom.eticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findAllByBookingCodeAndEvent(String bookingCode, Event event);

    Ticket findAllByTicketSerial(String ticketSerial);
}
