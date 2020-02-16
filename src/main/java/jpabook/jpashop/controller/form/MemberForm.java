package jpabook.jpashop.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입력사항입니다.")
    private String name;

    @NotEmpty(message = "등록 번호는 필수 입력사항입니다.")
    private String ssn;

    private String city;
    private String street;
    private String zipcode;
}
