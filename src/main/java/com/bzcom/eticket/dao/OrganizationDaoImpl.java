package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Organization;
import com.bzcom.eticket.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    @Autowired
    private OrganizationRepository repository;

    @Override
    public List<Organization> findAll() {
        return repository.findAll();
    }

    @Override
    public Organization findById(int id) {
        return repository.getOne(id);
    }

    @Override
    public Organization save(Organization organization) {
        return repository.save(organization);
    }
}
