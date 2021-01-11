package com.bzcom.eticket.service;

import com.bzcom.eticket.model.Seat;

import java.util.List;

public interface SeatService {

    List<Seat> findAll();

    Seat findById(int id);

    Seat save(Seat seat);

    Integer getSeatEmptyOfArea(int areaId, int eventId);

    Integer getSeatIdMin(int areaId);

}
