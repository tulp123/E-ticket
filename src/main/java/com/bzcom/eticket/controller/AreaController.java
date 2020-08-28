package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.Area;
import com.bzcom.eticket.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/areas"})
@CrossOrigin("http://localhost:3000")
public class AreaController {

    @Autowired
    private AreaService areaService;

    // Get all Area
    @GetMapping
    public List<Area> getAllAreas(){
        return areaService.findAll();
    }

    // Get a Single Area
    @GetMapping("/{id}")
    public Area getAreaById(@PathVariable(value = "id") int id){
        return areaService.findById(id);
    }

    // Create a new Area
    @PostMapping
    public Area createArea(@RequestBody Area area){
        return areaService.save(area);
    }

    // Update a Area
    @PutMapping("/{id}")
    public Area updateArea(@PathVariable(value = "id") int id, @RequestBody Area area){
        area.setId(id);
        Area updateArea = areaService.save(area);
        return updateArea;
    }

}
