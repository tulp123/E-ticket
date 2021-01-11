package com.bzcom.eticket.controller;

import com.bzcom.eticket.dto.AreaCountTicketDTO;
import com.bzcom.eticket.dto.MatchDTO;
import com.bzcom.eticket.model.*;
import com.bzcom.eticket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/events"})
@CrossOrigin("*")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private AreaPriceService areaPriceService;

    @Autowired
    private AreaService areaService;

    // Get All Event
    @GetMapping
    public List<MatchDTO> getAllEvents() {
        List<Event> events = eventService.findAll();
        List<MatchDTO> matchDTOS = new ArrayList<>();
        for (Event event : events) {
            MatchDTO matchDTO = new MatchDTO(event);
            matchDTOS.add(matchDTO);
        }
        return matchDTOS;
    }

    @GetMapping("/sort-up")
    public List<Event> getAllEventsSortUp() {
        return eventService.findAllByMatchTimeAsc();
    }

    @GetMapping("/sort-down")
    public List<Event> getAllEventsSortDown() {
        return eventService.findAllByMatchTimeDesc();
    }

    @GetMapping("/team/{id}")
    public List<MatchDTO> getEventByTeamId(@PathVariable int id) {
        List<Event> events = eventService.findEventsByTeamId(id);
        return events.stream().map(event -> new MatchDTO(event)).collect(Collectors.toList());
    }

    // Get a Single Event
    @GetMapping("/{id}")
    public MatchDTO getEventById(@PathVariable(value = "id") Integer id) {
        Event event = eventService.findById(id);
        List<AreaCountTicketDTO> remainTicketDTOList = areaService.areaCountRemainTicket(id);
        Collection<AreaPrice> areaPrices = event.getAreaPrices();

        for (AreaPrice areaPrice : areaPrices) {
            for (AreaCountTicketDTO areaCountTicketDTO : remainTicketDTOList) {
                if (areaPrice.getId().getAreaId() == areaCountTicketDTO.getAreaId()) {
                    areaPrice.setRemainTicket((int) areaCountTicketDTO.getTotalTicket());
                    break;
                }
            }
        }

        MatchDTO matchDTO = new MatchDTO(event);
        return matchDTO;
    }

    // Get list area price of event
    @GetMapping("/area-price/{id}")
    public List<AreaPrice> findByEventId(@PathVariable(value = "id") int eventId) {
        return areaPriceService.findByEventId(eventId);
    }

    // Save event-area-price to database
    @PostMapping("/save-price")
    @Transactional
    public List<AreaPrice> save(@RequestBody List<AreaPrice> areaPrices) {
        List<AreaPrice> listSave = new ArrayList<>();
        for (AreaPrice areaPrice : areaPrices) {
            Area area = new Area(areaPrice.getId().getAreaId());
            Event event = new Event(areaPrice.getId().getEventId());

            areaPrice.setArea(area);
            areaPrice.setEvent(event);
            listSave.add(areaPrice);
        }
        return areaPriceService.saveAll(listSave);
    }

    // Create a new Event
    @PostMapping
    public Event saveEvent(@RequestBody Event event) {
        Game game = event.getGame();
        game.setEvent(event);
        return eventService.save(event);
    }

    // Update a Event
    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable(value = "id") Integer eventId, @RequestBody Event eventDetail) {
        eventDetail.setId(eventId);
        Event eventUpdate = eventService.save(eventDetail);
        return eventUpdate;
    }

    // Disable a Event
    @PutMapping("/{id}/disable")
    public Event disableEvent(@PathVariable(value = "id") Integer eventId) {
        Event event = eventService.findById(eventId);
        event.setEventStatus(false);
        eventService.save(event);
        return event;
    }


}
