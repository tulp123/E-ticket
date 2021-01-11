package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.UserDao;
import com.bzcom.eticket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> searchUser(String name) {
        return userDao.searchUser(name);
    }

//    @Override
//    public User getByUsername(String username) {
//        return userDao.getByUsername(username);
//    }

    @Override
    public User findUserByPhoneNumber(String phoneNum) {
        return userDao.findUserByPhoneNumber(phoneNum);
    }

    @Override
    public List<User> findUserByOrganizationId(int organizationId) {
        return userDao.findUserByOrganizationId(organizationId);
    }

}
