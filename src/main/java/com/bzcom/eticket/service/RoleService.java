package com.bzcom.eticket.service;

import com.bzcom.eticket.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(int id);

    Role save(Role role);

}
