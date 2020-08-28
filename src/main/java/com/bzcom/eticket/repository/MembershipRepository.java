package com.bzcom.eticket.repository;

import com.bzcom.eticket.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository  extends JpaRepository<Membership, Integer> {
}
