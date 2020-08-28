package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Membership;

import java.util.List;

public interface MembershipDao {

    List<Membership> findAll();

    Membership findById(int id);

    Membership save(Membership membership);

}
