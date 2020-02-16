package jpabook.jpashop.controller.form;

import jpabook.jpashop.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class MemberListForm {

    private List<Member> members;
}
