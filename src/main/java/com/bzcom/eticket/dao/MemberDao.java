package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Member;

import java.util.List;

public interface MemberDao {
    Member findById(int id);

    List<Member> findAll();

    Member save(Member member);

    List<Member> getMemberByRole(int roleId);

    boolean checkExistUsername(String username);

    List<Member> findAllByCreatedDateAsc();

    List<Member> findAllByCreatedDateDesc();

    List<Member> findAllByConditions(String searchKey);

    Member findMemberByImeiMember(Long imei);
}
