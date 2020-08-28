package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.GameDao;
import com.bzcom.eticket.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;

    @Override
    public List<Game> findAll() {
        return gameDao.findAll();
    }

    @Override
    public Game findById(int id) {
        return gameDao.findById(id);
    }

    @Override
    public Game save(Game game) {
        return gameDao.save(game);
    }
}
