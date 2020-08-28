package com.bzcom.eticket.service;

import com.bzcom.eticket.model.Team;

import java.util.List;

public interface TeamService {

    List<Team> findAll();

    Team findById(int id);

    Team save(Team team);
}
