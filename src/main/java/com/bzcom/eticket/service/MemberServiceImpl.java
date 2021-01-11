package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.MemberDao;
import com.bzcom.eticket.dao.RoleDao;
import com.bzcom.eticket.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findById(int id) {
        return memberDao.findById(id);
    }

    @Override
    public List<Member> findAll() {
        return memberDao.findAll();
    }

    @Override
    public Member save(Member member) {
        return memberDao.save(member);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return memberDao.checkExistUsername(username);
    }

    @Override
    public List<Member> getMemberByRole(int roleId) {
        return memberDao.getMemberByRole(roleId);
    }

    @Override
    public List<Member> findAllByCreatedDateAsc() {
        return memberDao.findAllByCreatedDateAsc();
    }

    @Override
    public List<Member> findAllByCreatedDateDesc() {
        return memberDao.findAllByCreatedDateDesc();
    }

    @Override
    public List<Member> findAllByConditions(String searchKey) {
        return memberDao.findAllByConditions(searchKey);
    }

    @Override
    public Member findMemberByImeiMember(Long imei) {
        return memberDao.findMemberByImeiMember(imei);
    }
}
