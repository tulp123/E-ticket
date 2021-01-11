package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.GateDao;
import com.bzcom.eticket.model.Area;
import com.bzcom.eticket.model.Gate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateServiceImpl implements GateService {

    @Autowired
    private GateDao gateDao;

    @Override
    public List<Gate> findAll() {
        return gateDao.findAll();
    }

    @Override
    public Gate findById(int id) {
        return gateDao.findById(id);
    }

    @Override
    public Gate save(Gate gate) {
        return gateDao.save(gate);
    }

    @Override
    public List<Gate> findAllByArea(Area area) {
        return gateDao.findAllByArea(area);
    }
}
