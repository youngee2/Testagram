package com.newsfeed.testagram.domain.member.dto.response;

import com.newsfeed.testagram.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 회원 정보 응답 DTO 클래스 입니다.
 * 클라이언트에 반환할 회원의 email, image, nickname 정보를 담고 있습니다.
 */
public class MemberResponseDto {
    private String email;
    private String nickname;
    private String image;


    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(
                member.getEmail(),
                member.getNickname(),
                member.getImage()
        );
    }
}

