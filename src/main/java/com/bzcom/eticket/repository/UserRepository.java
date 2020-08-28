package com.bzcom.eticket.repository;

import com.bzcom.eticket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByFullNameContains(String name);

    User findUserByPhoneNumber(String phoneNum);
}
