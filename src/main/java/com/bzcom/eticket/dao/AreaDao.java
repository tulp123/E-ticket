package com.bzcom.eticket.dao;

import com.bzcom.eticket.dto.AreaCountTicketDTO;
import com.bzcom.eticket.model.Area;
import com.bzcom.eticket.model.Location;

import java.util.List;

public interface AreaDao {

    List<Area> findAll();

    Area findById(int id);

    Area save(Area area);

    List<Area> findAllByLocation(Location location);

    List<AreaCountTicketDTO> areaCountRemainTicket(int eventId);

}
