package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.League;

import java.util.List;

public interface LeagueDao {

    List<League> findAll();

    League findById(int id);

    League save(League league);

}
