package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.LocationDao;
import com.bzcom.eticket.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDao locationDao;

    @Override
    public Location findById(int id) {
        return locationDao.findById(id);
    }

    @Override
    public List<Location> findAll() {
        return locationDao.findAll();
    }

    @Override
    public Location save(Location location) {
        return locationDao.save(location);
    }
}
