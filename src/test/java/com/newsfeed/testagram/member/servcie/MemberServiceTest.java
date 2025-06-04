package com.newsfeed.testagram.member.servcie;

import com.newsfeed.testagram.common.security.PasswordEncoder;
import com.newsfeed.testagram.common.util.JwtUtil;
import com.newsfeed.testagram.domain.member.repository.MemberRepository;
import com.newsfeed.testagram.domain.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    JwtUtil jwtUtil;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    MemberRepository memberRepository;

    MemberService memberService;

    @BeforeEach
    public void init(){
        memberService = new MemberService(jwtUtil,memberRepository,passwordEncoder);
    }

    @Test
    public void 회원가입(){
        String email = "test@mail.com";
        String password = "Password!23";
        String nick = "kun";
        //g
        when(memberRepository.findByEmail(email)).thenReturn(Optional.empty());
        //w
        memberService.signup(email,password,nick);
        //t
        System.out.printf("로그인 성공");
    }
}
