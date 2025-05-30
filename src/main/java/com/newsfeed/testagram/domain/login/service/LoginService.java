package com.newsfeed.testagram.domain.login.service;

import com.newsfeed.testagram.common.exception.login.IncorrectPasswordException;
import com.newsfeed.testagram.common.security.PasswordEncoder;
import com.newsfeed.testagram.common.util.JwtUtil;
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
    private final JwtUtil jwtUtil;

    public LoginResponse login(String email, String password) {
        Member member = memberRepository.findByEmailOrThrow(email);
        if ( !passwordEncoder.matches(password,member.getPassword())){
            throw new IncorrectPasswordException();
        }

        String token = jwtUtil.createToken(email);

        return new LoginResponse(token);
    }
}
