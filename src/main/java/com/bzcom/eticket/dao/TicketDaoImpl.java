package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Event;
import com.bzcom.eticket.model.Ticket;
import com.bzcom.eticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Ticket> findAllByIds(List<Integer> ids) {
        return ticketRepository.findAllById(ids);
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
    public List<Ticket> findByBookingCode(String bookingCode, Event event) {
        return ticketRepository.findAllByBookingCodeAndEvent(bookingCode, event);
    }

    @Override
    public Ticket findByTicketSerial(String ticketSerial) {
        return ticketRepository.findAllByTicketSerial(ticketSerial);
    }

    @Override
    public String getMaxSerial(int eventId) {
        TypedQuery<String> q = entityManager.createQuery(
                "select max(t.ticketSerial) from Ticket t where t.event.id = ?1", String.class);
        q.setParameter(1, eventId);
        return q.getSingleResult();
    }

    @Override
    public List<Ticket> findTicketByBookingCodeAndUser_EmailAndUser_Phone( String bookingCode, String email, String phoneNumber) {
        Query query = entityManager.createQuery("select t from Ticket as t join User as u on u.id = t.user.id where t.bookingCode = ?1 and u.email = ?2 and u.phoneNumber = ?3")
                .setParameter(1 , bookingCode)
                .setParameter(2 , email)
                .setParameter(3 , phoneNumber);
        List<Ticket> results = query.getResultList();
        return results;
    }

    @Override
    @Transactional
    public List<Ticket> getListTicketAvailable(int eventId, int areaId, int numberTicket) {
        Query q = entityManager.createQuery(
                "select t from Ticket t where t.event.id = ?1 and t.seat.id in (select s.id from Seat s where s.area.id = ?2) " +
                        "and t.bookingStatus in (0, 3) order by t.id");
        q.setParameter(1, eventId);
        q.setParameter(2, areaId);
        q.setLockMode(LockModeType.OPTIMISTIC);
        return q.setMaxResults(numberTicket).getResultList();
    }

    @Override
    @Transactional
    public void updateBookingStatus(int bookingStatus, List<Ticket> tickets) {
        List<Integer> ids = tickets.stream().map(Ticket::getId).collect(Collectors.toList());
        Query q = entityManager.createQuery(
                "update Ticket t set t.bookingStatus = ?1 where t.id in (?2)");
        q.setParameter(1, bookingStatus);
        q.setParameter(2, ids);
        q.executeUpdate();
    }
}
