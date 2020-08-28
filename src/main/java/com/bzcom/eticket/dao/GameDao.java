package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Game;

import java.util.List;

public interface GameDao {
    List<Game> findAll();

    Game findById(int id);

    Game save(Game game);
}
