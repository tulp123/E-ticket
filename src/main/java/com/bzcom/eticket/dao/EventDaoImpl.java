package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Event;
import com.bzcom.eticket.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EventDaoImpl implements EventDao {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> findAll() {
        Query query = entityManager.createQuery("select event from Event as event where event.eventStatus = false");
        List<Event> results = query.getResultList();
        return results;
    }

    @Override
    public List<Event> findAllByMatchTimeAsc() {
        Query query = entityManager.createQuery("select event from Event as event where event.eventStatus = false order by event.game.matchTime asc");
        List<Event> results = query.getResultList();
        return results;
    }

    @Override
    public List<Event> findAllByMatchTimeDesc() {
        Query query = entityManager.createQuery("select event from Event as event where event.eventStatus = false order by event.game.matchTime desc");
        List<Event> results = query.getResultList();
        return results;
    }

    @Override
    public Event findById(int id) {
        return eventRepository.getOne(id);
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> findEventsByTeamId(int id) {
        Query query = entityManager.createNativeQuery("select * from event where id in " +
                "(select id from game where game.team_a_id = ?1)", Event.class)
                .setParameter(1, id);
        List<Event> results = query.getResultList();
        return results;
    }

}
