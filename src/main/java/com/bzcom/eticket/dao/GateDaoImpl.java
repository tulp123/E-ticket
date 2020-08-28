package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Gate;
import com.bzcom.eticket.repository.GateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GateDaoImpl implements GateDao {

    @Autowired
    private GateRepository gateRepository;

    @Override
    public List<Gate> findAll() {
        return gateRepository.findAll();
    }

    @Override
    public Gate findById(int id) {
        return gateRepository.getOne(id);
    }

    @Override
    public Gate save(Gate gate) {
        return gateRepository.save(gate);
    }
}
