package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.OrganizationDao;
import com.bzcom.eticket.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public List<Organization> findAll() {
        return organizationDao.findAll();
    }

    @Override
    public Organization findById(int id) {
        return organizationDao.findById(id);
    }

    @Override
    public Organization save(Organization organization) {
        return organizationDao.save(organization);
    }
}
