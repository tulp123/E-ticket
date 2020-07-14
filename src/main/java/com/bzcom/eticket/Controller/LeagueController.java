package com.bzcom.eticket.Controller;

import com.bzcom.eticket.Exception.LeagueNotFoundException;
import com.bzcom.eticket.Model.League;
import com.bzcom.eticket.Repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeagueController {
    @Autowired
    LeagueRepository leagueRepository;
    // Get All League
    @GetMapping("/leagues")
    public List<League> getAllNotes() {
        return leagueRepository.findAll();
    }
//
//    // Create a new League
//    @PostMapping("/leagues")
//    public League createNote(@RequestBody League league) {
//        return leagueRepository.save(league);
//    }
//
//    // Get a Single League
//    @GetMapping("/leagues/{id}")
//    public League getNoteById(@PathVariable(value = "id") Integer leagueId) throws LeagueNotFoundException {
//        return leagueRepository.findById(leagueId)
//                .orElseThrow(() -> new LeagueNotFoundException(leagueId));
//    }
//
//    // Update a League
//    @PutMapping("/leagues/{id}")
//    public League updateNote(@PathVariable(value = "id") Integer leagueId,
//                            @RequestBody League leagueDetail) throws LeagueNotFoundException {
//
//        League league = leagueRepository.findById(leagueId)
//                .orElseThrow(() -> new LeagueNotFoundException(leagueId));
//
//        league.setLeague_name(leagueDetail.getLeague_name());
//        league.setLeague_info(leagueDetail.getLeague_info());
//
//        League updateLeague = leagueRepository.save(league);
//
//        return updateLeague;
//    }
//
//    // Delete a Note
//    @DeleteMapping("/books/{id}")
//    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Integer leagueId) throws LeagueNotFoundException {
//        League league = leagueRepository.findById(leagueId)
//                .orElseThrow(() -> new LeagueNotFoundException(leagueId));
//
//        leagueRepository.delete(league);
//
//        return ResponseEntity.ok().build();
//    }
}
