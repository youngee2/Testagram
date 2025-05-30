package com.newsfeed.testagram.member.service;
import com.newsfeed.testagram.common.exception.member.MemberNotMatchedException;
import com.newsfeed.testagram.common.exception.member.EmailAlreadyExistsException;
import com.newsfeed.testagram.common.exception.member.MemberNotFoundException;
import com.newsfeed.testagram.common.security.PasswordEncoder;
import com.newsfeed.testagram.member.dto.response.MemberResponseDto;
import com.newsfeed.testagram.member.dto.response.MyProfileResponseDto;
import com.newsfeed.testagram.member.entity.Member;
import com.newsfeed.testagram.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.newsfeed.testagram.domain.member.dto.MemberSignUpResponse;

/**
 * 회원 서비스 구현 클래스입니다.
 * 회원 관련 비지니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    /**
     * @param id 조회할 회원의 id
     * @return 조회된 회원 정보를 담은 MemberResponseDto
     * @throws MemberNotFoundException 해당 ID의 회원이 존재하지 않을 경우 예외 발생합니다.
     */

    @Override
    public MemberResponseDto getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
        return MemberResponseDto.of(member);
    }


    @Override
    public MemberSignUpResponse signup(String email, String password, String nickname){
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        Member member = new Member(email, passwordEncoder.encode(password), nickname);

        memberRepository.save(member);
        return MemberSignUpResponse.toDto("회원가입이 완료되었습니다.", member);
    }

    @Override
    public MyProfileResponseDto getMyProfileById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(MemberNotMatchedException::new);
        return MyProfileResponseDto.of(member);
    }


}
