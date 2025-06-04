package com.newsfeed.testagram.domain.member.dto.request;


import lombok.Getter;

/**
 * 프로필 조회 요청 DTO 입니다.
 */
@Getter
public class MyProfileRequestDto {
    private String nickname;
    private String image;
}
