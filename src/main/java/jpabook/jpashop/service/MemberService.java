package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * sign up new member
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findBySsn(member.getSsn());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("already a registered member");
        }
    }

    /**
     * view all member
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * view a member by id
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    /**
     * view a member by name
     */
    public List<Member> findByName(String memberName) {
        return memberRepository.findByName(memberName);
    }

    /**
     * update the name of a member by using dirty checking
     */
    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
