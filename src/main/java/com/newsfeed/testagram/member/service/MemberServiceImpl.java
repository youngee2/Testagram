package com.newsfeed.testagram.member.service;

import com.newsfeed.testagram.common.exception.member.MemberNotFoundException;
import com.newsfeed.testagram.member.dto.response.MemberResponseDto;
import com.newsfeed.testagram.member.entity.Member;
import com.newsfeed.testagram.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 회원 서비스 구현 클래스입니다.
 * 회원 관련 비지니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    /**
     *
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
}
