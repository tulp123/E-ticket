package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> findAll();

    Role findById(int id);

    Role save(Role role);

}
