package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Membership;
import com.bzcom.eticket.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MembershipDaoImpl implements MembershipDao {

    @Autowired
    private MembershipRepository membershipRepository;

    @Override
    public List<Membership> findAll() {
        return membershipRepository.findAll();
    }

    @Override
    public Membership findById(int id) {
        return membershipRepository.getOne(id);
    }

    @Override
    public Membership save(Membership membership) {
        return membershipRepository.save(membership);
    }
}
