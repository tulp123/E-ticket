package com.bzcom.eticket.service;

import com.bzcom.eticket.model.ERole;
import com.bzcom.eticket.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> findAll();

    Role findById(int id);

    Role save(Role role);

    Optional<Role> findByName(ERole name);
}
