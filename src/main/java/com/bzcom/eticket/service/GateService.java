package com.bzcom.eticket.service;

import com.bzcom.eticket.model.Gate;

import java.util.List;

public interface GateService {

    List<Gate> findAll();

    Gate findById(int id);

    Gate save(Gate gate);

}
