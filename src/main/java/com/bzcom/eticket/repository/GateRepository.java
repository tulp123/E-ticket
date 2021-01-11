package com.bzcom.eticket.repository;

import com.bzcom.eticket.model.Area;
import com.bzcom.eticket.model.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GateRepository extends JpaRepository<Gate, Integer> {
    List<Gate> findAllByArea(Area area);
}
