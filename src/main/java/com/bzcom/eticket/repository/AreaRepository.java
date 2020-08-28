package com.bzcom.eticket.repository;

import com.bzcom.eticket.model.Area;
import com.bzcom.eticket.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {

    List<Area> findAllByLocation(Location location);
}
