package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Organization;

import java.util.List;

public interface OrganizationDao {

    List<Organization> findAll();

    Organization findById(int id);

    Organization save(Organization organization);
}
