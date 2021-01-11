package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Seat;
import com.bzcom.eticket.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SeatDaoImpl implements SeatDao{

    @Autowired
    private SeatRepository seatRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    @Override
    public Seat findById(int id) {
        return seatRepository.getOne(id);
    }

    @Override
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public Integer getSeatEmptyOfArea(int areaId, int eventId) {
        TypedQuery<Integer> query = entityManager.createQuery("SELECT min(s.id) FROM Seat s where s.area.id = ?1 and " +
                "s.id NOT IN (select t.seat.id from Ticket t where t.event.id = ?2)", Integer.class);
        query.setParameter(1, areaId).setParameter(2, eventId);
        return query.getSingleResult();
    }

    @Override
    public Integer getSeatIdMin(int areaId) {
        TypedQuery<Integer> query = entityManager.createQuery("SELECT min(s.id) FROM Seat s where s.area.id = ?1", Integer.class);
        query.setParameter(1, areaId);
        return query.getSingleResult();
    }
}
