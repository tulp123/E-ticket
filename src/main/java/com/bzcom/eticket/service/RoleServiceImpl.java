package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.RoleDao;
import com.bzcom.eticket.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }
}
