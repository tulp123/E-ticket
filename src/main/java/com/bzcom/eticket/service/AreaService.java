package com.bzcom.eticket.service;

import com.bzcom.eticket.dto.AreaCountTicketDTO;
import com.bzcom.eticket.model.Area;
import com.bzcom.eticket.model.Location;

import java.util.List;

public interface AreaService {

    List<Area> findAll();

    Area findById(int id);

    Area save(Area area);

    List<Area> findAllByLocation(Location location);

    List<AreaCountTicketDTO> areaCountTicket(int eventId);

}
