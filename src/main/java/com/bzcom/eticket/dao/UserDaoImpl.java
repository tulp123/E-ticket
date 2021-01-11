package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.User;
import com.bzcom.eticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> searchUser(String name) {
        return userRepository.findAllByFullNameContains(name);
    }

//    @Override
//    public User getByUsername(String username) {
//        TypedQuery<User> q = entityManager.createQuery(
//                "select user from User user  where user.userStatus = 1 and user.username = :username", User.class);
//        q.setParameter("username", username);
//        return q.getSingleResult();
//    }

    @Override
    public User findUserByPhoneNumber(String phoneNum) {
        return userRepository.findUserByPhoneNumber(phoneNum);
    }

    @Override
    public List<User> findUserByOrganizationId(int organizationId) {
        TypedQuery<User> q = entityManager.createQuery(
                "select user from User user  where user.organization.id = :organizationId", User.class);
        q.setParameter("organizationId", organizationId);
        return q.getResultList();
    }

}
