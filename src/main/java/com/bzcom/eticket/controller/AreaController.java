package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.Area;
import com.bzcom.eticket.model.Gate;
import com.bzcom.eticket.service.AreaService;
import com.bzcom.eticket.service.GameService;
import com.bzcom.eticket.service.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/areas"})
@CrossOrigin("*")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @Autowired
    private GateService gateService;

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

    // Get all Gates of a Single Area
    @GetMapping("/{id}/gates")
    public List<Gate> getAllGatesByAreaId(@PathVariable(value = "id") int id){
        Area area = areaService.findById(id);
        List<Gate> gates = gateService.findAllByArea(area);
        return gates;
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
