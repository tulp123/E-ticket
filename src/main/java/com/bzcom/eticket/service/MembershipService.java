package com.bzcom.eticket.service;

import com.bzcom.eticket.model.Membership;

import java.util.List;

public interface MembershipService {

    List<Membership> findAll();

    Membership findById(int id);

    Membership save(Membership membership);
}
