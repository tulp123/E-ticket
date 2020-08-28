package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.MembershipDao;
import com.bzcom.eticket.model.Membership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private MembershipDao membershipDao;

    @Override
    public List<Membership> findAll() {
        return membershipDao.findAll();
    }

    @Override
    public Membership findById(int id) {
        return membershipDao.findById(id);
    }

    @Override
    public Membership save(Membership membership) {
        return membershipDao.save(membership);
    }
}
