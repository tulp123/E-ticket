package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.League;
import com.bzcom.eticket.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeagueDaoImpl implements LeagueDao {

    @Autowired
    private LeagueRepository leagueRepository;

    @Override
    public List<League> findAll() {
        return leagueRepository.findAll();
    }

    @Override
    public League findById(int id) {
        return leagueRepository.getOne(id);
    }

    @Override
    public League save(League league) {
        return leagueRepository.save(league);
    }
}
