package com.bzcom.eticket.repository;

import com.bzcom.eticket.model.ERole;
import com.bzcom.eticket.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);

    Role findById(int id);
}
