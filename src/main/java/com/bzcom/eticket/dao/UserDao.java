package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    User findById(int id);

    User save(User user);

    List<User> searchUser(String name);

//    User getByUsername(String username);

    User findUserByPhoneNumber(String phoneNum);

}
