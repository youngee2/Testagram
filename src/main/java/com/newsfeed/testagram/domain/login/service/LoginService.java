package com.newsfeed.testagram.domain.login.service;

import com.newsfeed.testagram.common.config.PasswordEncoder;
import com.newsfeed.testagram.common.exception.login.IncorrectPasswordException;
import com.newsfeed.testagram.domain.login.dto.LoginResponse;
import com.newsfeed.testagram.domain.member.entity.Member;
import com.newsfeed.testagram.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(String email, String password) {
        Member member = memberRepository.findByEmailOrThrow(email);
        if ( !passwordEncoder.matches(password,member.getPassword())){
            throw new IncorrectPasswordException();
        }
        return new LoginResponse("로그인 되었습니다.");
    }
}
