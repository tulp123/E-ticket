package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.AreaDao;
import com.bzcom.eticket.dto.AreaCountTicketDTO;
import com.bzcom.eticket.model.Area;
import com.bzcom.eticket.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> findAll() {
        return areaDao.findAll();
    }

    @Override
    public Area findById(int id) {
        return areaDao.findById(id);
    }

    @Override
    public Area save(Area area) {
        return areaDao.save(area);
    }

    @Override
    public List<Area> findAllByLocation(Location location) {
        return areaDao.findAllByLocation(location);
    }

    @Override
    public List<AreaCountTicketDTO> areaCountTicket(int eventId) {
        return areaDao.areaCountTicket(eventId);
    }
}
