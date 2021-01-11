package com.bzcom.eticket.controller;

import com.bzcom.eticket.dto.AreaCountTicketDTO;
import com.bzcom.eticket.dto.StatisticDTO;
import com.bzcom.eticket.model.Event;
import com.bzcom.eticket.service.AreaService;
import com.bzcom.eticket.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/statistics"})
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private EventService eventService;

    @Autowired
    private AreaService areaService;

    @GetMapping
    public List<StatisticDTO> ticketDtoList () {
        List<Event> eventList = eventService.findAll();
        List<StatisticDTO> dtos = new ArrayList<>();
        for (int i = 0; i < eventList.size(); i++) {
            StatisticDTO statisticDTO = new StatisticDTO();
            statisticDTO.setEventId(eventList.get(i).getId());
            statisticDTO.setTeamB(eventList.get(i).getGame().getTeamB().getName());

            List<AreaCountTicketDTO> areaCountTicketDTOList = areaService.areaCountRemainTicket(eventList.get(i).getId());
            for (AreaCountTicketDTO areaCountTicketDTO : areaCountTicketDTOList){
                switch (areaCountTicketDTO.getAreaId()){
                    case 1:
                        statisticDTO.setTotalTicketZoneA((int) areaCountTicketDTO.getTotalTicket());
                        break;
                    case 2:
                        statisticDTO.setTotalTicketZoneB((int) areaCountTicketDTO.getTotalTicket());
                        break;
                    case 3:
                        statisticDTO.setTotalTicketZoneC((int) areaCountTicketDTO.getTotalTicket());
                        break;
                    case 4:
                        statisticDTO.setTotalTicketZoneD((int) areaCountTicketDTO.getTotalTicket());
                        break;
                    default:
                        break;
                }
            }

            dtos.add(statisticDTO);
        }
        return dtos;
    }
}
