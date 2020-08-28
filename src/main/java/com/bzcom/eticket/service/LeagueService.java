package com.bzcom.eticket.service;

import com.bzcom.eticket.model.League;

import java.util.List;

public interface LeagueService {

    List<League> findAll();

    League findById(int id);

    League save(League league);

}
