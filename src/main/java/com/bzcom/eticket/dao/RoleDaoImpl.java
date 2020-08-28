package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Role;
import com.bzcom.eticket.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(int id) {
        return roleRepository.getOne(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
