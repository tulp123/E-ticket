package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.LeagueDao;
import com.bzcom.eticket.model.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private LeagueDao leagueDao;

    @Override
    public List<League> findAll() {
        return leagueDao.findAll();
    }

    @Override
    public League findById(int id) {
        return leagueDao.findById(id);
    }

    @Override
    public League save(League league) {
        return leagueDao.save(league);
    }
}
