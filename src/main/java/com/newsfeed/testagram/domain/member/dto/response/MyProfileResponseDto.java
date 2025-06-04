package com.newsfeed.testagram.domain.member.dto.response;

import com.newsfeed.testagram.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 로그인한 사용자의 정보를 조회하는 응답 DTO입니다.
 */
@Getter
@AllArgsConstructor
public class MyProfileResponseDto {
    private Long id;
    private String email;
    private String nickname;
    private String image;
    private LocalDateTime createdAt;

    public static MyProfileResponseDto of(Member member) {
        return new MyProfileResponseDto(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getImage(),
                member.getCreatedAt()
        );
    }
}
