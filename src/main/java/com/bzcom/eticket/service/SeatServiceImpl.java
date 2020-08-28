package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.SeatDao;
import com.bzcom.eticket.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatDao seatDao;

    @Override
    public List<Seat> findAll() {
        return seatDao.findAll();
    }

    @Override
    public Seat findById(int id) {
        return seatDao.findById(id);
    }

    @Override
    public Seat save(Seat seat) {
        return seatDao.save(seat);
    }
}
