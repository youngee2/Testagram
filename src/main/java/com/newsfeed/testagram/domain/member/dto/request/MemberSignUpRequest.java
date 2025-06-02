package com.newsfeed.testagram.domain.member.dto.request;

import com.newsfeed.testagram.common.valid.EmailValid;
import com.newsfeed.testagram.common.valid.PasswordValid;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class MemberSignUpRequest {
    @Pattern(regexp = EmailValid.emailRegex, message = "유효하지 않는 이메일 입니다.")
    private String email;
    @Pattern(regexp = PasswordValid.passwordRegex,message = "비밀번호: 영문 대소문자 + 숫자 + 특수문자 1자 이상 포함, 최소 8자")
    private String password;
    private String nickname;
}
