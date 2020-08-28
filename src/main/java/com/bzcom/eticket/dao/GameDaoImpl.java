package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Game;
import com.bzcom.eticket.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameDaoImpl implements GameDao {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game findById(int id) {
        return gameRepository.getOne(id);
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }
}
