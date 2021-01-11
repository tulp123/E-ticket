package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Member;
import com.bzcom.eticket.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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

    @Override
    public List<Member> findAllByConditions(String searchKey) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);
        Root<Member> root = query.from(Member.class);

        List<Predicate> predicates = new ArrayList<>();

        if (!searchKey.isEmpty()) {
            predicates.add(cb.or(
                    cb.like(root.get("username"), "%" + searchKey + "%"),
                    cb.like(root.get("user").get("fullName"), "%" + searchKey + "%"),
                    cb.like(root.get("user").get("email"), "%" + searchKey + "%"),
                    cb.like(root.get("user").get("phoneNumber"), "%" + searchKey + "%")
                    )
            );
        }
        query.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Member> typedQuery = entityManager.createQuery(query);
        List<Member> results = typedQuery.getResultList();

        return results;
    }

    @Override
    public Member findMemberByImeiMember(Long imei) {
        return memberRepository.findMemberByImeiMember(imei);
    }
}
