package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Seat;

import java.util.List;

public interface SeatDao {

    List<Seat> findAll();

    Seat findById(int id);

    Seat save(Seat seat);
}
