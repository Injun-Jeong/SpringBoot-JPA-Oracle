package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;
    
    @Test
    public void joinTest() throws Exception {
        //given
        Member member = new Member();
        member.setSsn("2013019033");
        member.setName("injun");

        //when
        Long savedId = memberService.join(member);

        //then
        em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void duplicateJoinTest() throws Exception {
        //given
        Member member = new Member();
        member.setSsn("2013019033");
        member.setName("injun");

        Member newMember = new Member();
        newMember.setSsn("2013019033");
        newMember.setName("harry");
        
        //when
        memberService.join(member);

        //then
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(newMember);
        });
    }

}