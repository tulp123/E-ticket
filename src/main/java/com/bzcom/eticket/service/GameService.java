package com.bzcom.eticket.service;

import com.bzcom.eticket.model.Game;

import java.util.List;

public interface GameService {

    List<Game> findAll();

    Game findById(int id);

    Game save(Game game);

}
