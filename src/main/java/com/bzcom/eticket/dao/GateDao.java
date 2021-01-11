package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Area;
import com.bzcom.eticket.model.Gate;

import java.util.List;

public interface GateDao {

    List<Gate> findAll();

    Gate findById(int id);

    Gate save(Gate gate);

    List<Gate> findAllByArea(Area area);
}
