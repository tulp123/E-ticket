package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.Gate;
import com.bzcom.eticket.service.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/gates"})
@CrossOrigin("*")
public class GateController {
    @Autowired
    private GateService gateService;

    // Get All Gate
    @GetMapping
    public List<Gate> getAllGates(){
        return gateService.findAll();
    }

    // Get a Single Gate
    @GetMapping("/{id}")
    public Gate getGateById(@PathVariable(value = "id") Integer id) {
        return gateService.findById(id);
    }

    // Create a new Gate
    @PostMapping
    public Gate saveGate(@RequestBody Gate gate){
        return gateService.save(gate);
    }

    // Update a Gate
    @PutMapping("/{id}")
    public Gate updateGate(@PathVariable(value = "id") Integer id, @RequestBody Gate gate) {
        gate.setId(id);
        Gate gateUpdate = this.gateService.save(gate);
        return gateUpdate;
    }

}
