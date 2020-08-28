package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.Area;
import com.bzcom.eticket.model.Location;
import com.bzcom.eticket.service.AreaService;
import com.bzcom.eticket.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/locations"})
@CrossOrigin("http://localhost:3000")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private AreaService areaService;

    // Get all Location
    @GetMapping
    public List<Location> getAllLocations(){
        return locationService.findAll();
    }

    // Get a Single Location
    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable(value = "id") int id){
        return locationService.findById(id);
    }

    // Get all Areas of a Single Location
    @GetMapping("/{id}/areas")
    public List<Area> getAllAreasByLocationId(@PathVariable(value = "id") int id){
        Location location = locationService.findById(id);
        List<Area> areas = areaService.findAllByLocation(location);
        return areas;
    }

    // Create a new Location
    @PostMapping
    public Location createLocation(@RequestBody Location location){
        return locationService.save(location);
    }

    // Update a Location
    @PutMapping("/{id}")
    public Location updateLocation(@PathVariable(value = "id") int id, @RequestBody Location location){
        location.setId(id);
        Location updateLocation = locationService.save(location);
        return updateLocation;
    }

}
