package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Location;

import java.util.List;

public interface LocationDao {

    Location findById(int id);

    List<Location> findAll();

    Location save(Location location);

}
