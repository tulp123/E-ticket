package com.bzcom.eticket.service;

import com.bzcom.eticket.model.Location;

import java.util.List;

public interface LocationService {

    Location findById(int id);

    List<Location> findAll();

    Location save(Location location);

}
