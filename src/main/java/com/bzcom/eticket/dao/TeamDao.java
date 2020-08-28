package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Team;

import java.util.List;

public interface TeamDao {

    List<Team> findAll();

    Team findById(int id);

    Team save(Team team);

}
