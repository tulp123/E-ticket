package com.bzcom.eticket.service;

import com.bzcom.eticket.model.Organization;

import java.util.List;

public interface OrganizationService {

    List<Organization> findAll();

    Organization findById(int id);

    Organization save(Organization organization);
}
