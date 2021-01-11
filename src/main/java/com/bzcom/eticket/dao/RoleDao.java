package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.ERole;
import com.bzcom.eticket.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDao {

    List<Role> findAll();

    Role findById(int id);

    Role save(Role role);

    Optional<Role> finByName(ERole name);

}
