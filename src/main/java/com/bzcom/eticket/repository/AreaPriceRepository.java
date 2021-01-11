package com.bzcom.eticket.repository;

import com.bzcom.eticket.model.AreaPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaPriceRepository extends JpaRepository<AreaPrice, Integer> {
}
