package com.newsfeed.testagram.domain.member.service;

import com.newsfeed.testagram.common.exception.member.EmailAlreadyExistsException;
import com.newsfeed.testagram.common.security.PasswordEncoder;
import com.newsfeed.testagram.domain.member.dto.MemberSignUpResponse;
import com.newsfeed.testagram.domain.member.entity.Member;
import com.newsfeed.testagram.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberSignUpResponse signup(String email, String password, String nickname){
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        Member member = new Member(email, passwordEncoder.encode(password), nickname);
        memberRepository.save(member);
        return MemberSignUpResponse.toDto("회원가입이 완료되었습니다.", member);
    }
}
