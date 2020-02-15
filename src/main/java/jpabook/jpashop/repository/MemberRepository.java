package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        String jpql = "select m from Member m";
        return em.createQuery(jpql, Member.class).getResultList();
    }

    public List<Member> findByName(String name) {
        String jpql = "select m from Member m where m.name = :name";
        return em.createQuery(jpql, Member.class).setParameter("name", name).getResultList();
    }

    public List<Member> findBySsn(String ssn) {
        String jpql = "select m from Member m where m.ssn = :ssn";
        return em.createQuery(jpql, Member.class).setParameter("ssn", ssn).getResultList();
    }

}