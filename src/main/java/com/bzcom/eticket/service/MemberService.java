package com.bzcom.eticket.service;

import com.bzcom.eticket.model.Member;

import java.util.List;

public interface MemberService {
    Member findById(int id);

    List<Member> findAll();

    Member save(Member member);

    List<Member> getMemberByRole(int roleId);

    boolean checkExistUsername(String username);

    List<Member> findAllByCreatedDateAsc();

    List<Member> findAllByCreatedDateDesc();
}
