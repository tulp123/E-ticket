package com.bzcom.eticket.service;

import com.bzcom.eticket.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int id);

    User save(User user);

    List<User> searchUser(String name);

//    User getByUsername(String username);

    User findUserByPhoneNumber(String phoneNum);

    List<User> findUserByOrganizationId(int organizationId);

}
