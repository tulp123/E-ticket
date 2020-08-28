package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.Team;
import com.bzcom.eticket.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/teams"})
@CrossOrigin("http://localhost:3000")
public class TeamController {

    @Autowired
    private TeamService teamService;

    // Get All Team
    @GetMapping
    public List<Team> getAllTeams(){
        return teamService.findAll();
    }

    // Get a Single Team
    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable(value = "id") int teamId){
        return teamService.findById(teamId);
    }

    // Create a new Team
    @PostMapping
    public Team createTeam(@RequestBody Team team){
        return teamService.save(team);
    }

    // Update a Ticket
    @PutMapping("/{id}")
    public Team updateTeam(@PathVariable(value = "id") Integer id, @RequestBody Team team) {
        team.setId(id);
        Team teamUpdate = this.teamService.save(team);
        return teamUpdate;
    }

}
