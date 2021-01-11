package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.Game;
import com.bzcom.eticket.model.Location;
import com.bzcom.eticket.service.GameService;
import com.bzcom.eticket.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/games"})
@CrossOrigin("*")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private LocationService locationService;

    // Get All Game
    @GetMapping
    public List<Game> getAllGames(){
        return gameService.findAll();
    }

    // Get a Single Game
    @GetMapping("/{id}")
    public Game getGameById(@PathVariable(value = "id") Integer id) {
        return gameService.findById(id);
    }

    // Create a new Game
    @PostMapping
    public Game saveGame(@RequestBody Game game){
        return gameService.save(game);
    }

    // Update a Game
    @PutMapping("/{id}")
    public Game updateGame(@PathVariable(value = "id") Integer id, @RequestBody Game gameDetail) {
        gameDetail.setId(id);
        Game gameUpdate = gameService.save(gameDetail);
        return gameUpdate;
    }

}
