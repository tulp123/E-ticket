package com.bzcom.eticket.controller;

import com.bzcom.eticket.dto.AreaCountTicketDTO;
import com.bzcom.eticket.dto.TicketDTO;
import com.bzcom.eticket.model.Event;
import com.bzcom.eticket.service.AreaService;
import com.bzcom.eticket.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/statistics"})
@CrossOrigin("http://localhost:3000")
public class DashboardController {

    @Autowired
    private EventService eventService;

    @Autowired
    private AreaService areaService;

    @GetMapping
    public List<TicketDTO> ticketDtoList () {
        List<Event> eventList = eventService.findAll();
        List<TicketDTO> dtos = new ArrayList<>();
        for (int i = 0; i < eventList.size(); i++) {
            TicketDTO ticketDto = new TicketDTO();
            ticketDto.setEventId(eventList.get(i).getId());
            ticketDto.setTeamB(eventList.get(i).getGame().getTeamB().getName());

            List<AreaCountTicketDTO> areaCountTicketDTOList = areaService.areaCountTicket(eventList.get(i).getId());
            for (AreaCountTicketDTO areaCountTicketDTO : areaCountTicketDTOList){
                switch (areaCountTicketDTO.getAreaId()){
                    case 1:
                        ticketDto.setTotalTicketZoneA((int) areaCountTicketDTO.getTotalTicket());
                        break;
                    case 2:
                        ticketDto.setTotalTicketZoneB((int) areaCountTicketDTO.getTotalTicket());
                        break;
                    case 3:
                        ticketDto.setTotalTicketZoneC((int) areaCountTicketDTO.getTotalTicket());
                        break;
                    case 4:
                        ticketDto.setTotalTicketZoneD((int) areaCountTicketDTO.getTotalTicket());
                        break;
                    default:
                        break;
                }
            }

            dtos.add(ticketDto);
        }
        return dtos;
    }
}
