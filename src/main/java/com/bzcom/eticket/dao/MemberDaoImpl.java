package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Member;
import com.bzcom.eticket.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao{

    @Autowired
    private MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Member findById(int id) {
        return memberRepository.getOne(id);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return memberRepository.existsByUsername(username);
    }

    @Override
    public List<Member> getMemberByRole(int roleId) {
        Query query = entityManager.createQuery("select m from Member as m where m.role.id = ?1").setParameter(1, roleId);
        List<Member> results = query.getResultList();
        return results;
    }

    @Override
    public List<Member> findAllByCreatedDateAsc() {
        Query query = entityManager.createQuery("select m from Member as m order by m.createdDate asc");
        List<Member> results = query.getResultList();
        return results;
    }

    @Override
    public List<Member> findAllByCreatedDateDesc() {
        Query query = entityManager.createQuery("select m from Member as m order by m.createdDate desc");
        List<Member> results = query.getResultList();
        return results;
    }
}
