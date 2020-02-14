package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository // 리포지토리란 엔티티 같은 것을 찾아주는 놈인데, dto와 비슷하다고 생각해도 됨
public class MemberRepository {

    // 스프링 부트를 사용하는 중이기 때문에 스프링 컨테이너 위에서 동작한다.
    // 아래의 어노테이션을 통해 스프링이 엔티티 매니저 및 팩토리 생성하는 것을 자동으로 도와준다.
    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
