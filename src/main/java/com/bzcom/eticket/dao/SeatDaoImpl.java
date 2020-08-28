package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Seat;
import com.bzcom.eticket.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeatDaoImpl implements SeatDao{

    @Autowired
    private SeatRepository seatRepository;

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
}
