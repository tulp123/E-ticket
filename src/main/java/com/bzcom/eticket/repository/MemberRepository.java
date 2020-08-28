package com.bzcom.eticket.repository;

import com.bzcom.eticket.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    boolean existsByUsername(String username);
}
