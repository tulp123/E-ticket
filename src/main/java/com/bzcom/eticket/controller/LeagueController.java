package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.League;
import com.bzcom.eticket.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/leagues"})
@CrossOrigin("http://localhost:3000")
public class LeagueController {
    @Autowired
    private LeagueService leagueService;

    // Get All League
    @GetMapping
    public List<League> getAllLeagues() {
        return leagueService.findAll();
    }

    // Create a new League
    @PostMapping
    public League createLeague(@RequestBody League league) {
        return leagueService.save(league);
    }

    // Get a Single League
    @GetMapping("/{id}")
    public League getLeageById(@PathVariable(value = "id") Integer leagueId) {
        return leagueService.findById(leagueId);
    }

    // Update a League
    @PutMapping("/{id}")
    public League updateLeage(@PathVariable(value = "id") Integer leagueId, @RequestBody League leagueDetail) {

        leagueDetail.setId(leagueId);
        League updateLeague = leagueService.save(leagueDetail);

        return updateLeague;
    }

}
