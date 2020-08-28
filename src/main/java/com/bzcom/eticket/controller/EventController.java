package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.Event;
import com.bzcom.eticket.model.Game;
import com.bzcom.eticket.model.Location;
import com.bzcom.eticket.model.Team;
import com.bzcom.eticket.service.EventService;
import com.bzcom.eticket.service.LocationService;
import com.bzcom.eticket.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/events"})
@CrossOrigin("http://localhost:3000")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private TeamService teamService;

    // Get All Event
    @GetMapping
    public List<Event> getAllEvents(){
        return eventService.findAll();
    }

    @GetMapping("/sort-up")
    public List<Event> getAllEventsSortUp(){
        return eventService.findAllByMatchTimeAsc();
    }

    @GetMapping("/sort-down")
    public List<Event> getAllEventsSortDown(){
        return eventService.findAllByMatchTimeDesc();
    }

    // Get a Single Event
    @GetMapping("/{id}")
    public Event getEventById(@PathVariable(value = "id") Integer id) {
        return eventService.findById(id);
    }

    // Create a new Event
    @PostMapping
    public Event saveEvent(@RequestBody Location location){
        Location loc = locationService.findById(location.getId());
        List<Event> eventList = location.getEvents();
        Event event = eventList.get(0);
        Game game = event.getGame();

        int aTeamId = game.getaTeamId();
        int bTeamId = game.getbTeamId();
        Team teamA = new Team();
        Team teamB = new Team();
        teamA.setId(aTeamId);
        teamB.setId(bTeamId);

        game.setTeamA(teamA);
        game.setTeamB(teamB);
        game.setEvent(event);
        event.setEventStatus(true);
        event.setLocation(loc);
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
