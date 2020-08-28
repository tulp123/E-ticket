package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Team;
import com.bzcom.eticket.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TeamDaoImpl implements TeamDao {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Team> findAll() {
        Query query = entityManager.createQuery("select team from Team as team where team.id > 1");
        List<Team> results = query.getResultList();
        return results;
    }

    @Override
    public Team findById(int id) {
        return teamRepository.getOne(id);
    }

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

}
