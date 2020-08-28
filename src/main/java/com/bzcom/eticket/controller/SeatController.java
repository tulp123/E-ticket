package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.Seat;
import com.bzcom.eticket.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/seats"})
@CrossOrigin("http://localhost:3000")
public class SeatController {

    @Autowired
    private SeatService seatService;

    // Get All Seat
    @GetMapping
    public List<Seat> getAllSeats(){
        return seatService.findAll();
    }

    // Get a Single Seat
    @GetMapping("/{id}")
    public Seat getSeatById(@PathVariable(value = "id") Integer id) {
        return seatService.findById(id);
    }

    // Create a new Seat
    @PostMapping
    public Seat saveSeat(@RequestBody Seat seat){
        return seatService.save(seat);
    }

    // Update a Seat
    @PutMapping("/{id}")
    public Seat updateSeat(@PathVariable(value = "id") Integer seatId, @RequestBody Seat seatDetail) {
        seatDetail.setId(seatId);
        Seat seatUpdate = seatService.save(seatDetail);
        return seatUpdate;
    }


}
