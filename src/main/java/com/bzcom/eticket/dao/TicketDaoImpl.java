package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Event;
import com.bzcom.eticket.model.Ticket;
import com.bzcom.eticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TicketDaoImpl implements TicketDao {

    @Autowired
    private TicketRepository ticketRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(int id) {
        return ticketRepository.getOne(id);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> saveAll(List<Ticket> tickets) {
        return ticketRepository.saveAll(tickets);
    }

    @Override
    public Ticket findByBookingCode(String bookingCode, Event event) {
        return ticketRepository.findAllByBookingCodeAndEvent(bookingCode, event);
    }

    @Override
    public Ticket findByTicketSerial(String ticketSerial, Event event) {
        return ticketRepository.findAllByTicketSerialAndEvent(ticketSerial, event);
    }

    @Override
    public String getMaxSerial(int eventId) {
        TypedQuery<String> q = entityManager.createQuery(
                "select max(t.ticketSerial) from Ticket t where t.event.id = ?1", String.class);
        q.setParameter(1, eventId);
        return q.getSingleResult();
    }
}
