package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Location;
import com.bzcom.eticket.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationDaoImpl implements LocationDao {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location findById(int id) {
        return locationRepository.getOne(id);
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }
}
