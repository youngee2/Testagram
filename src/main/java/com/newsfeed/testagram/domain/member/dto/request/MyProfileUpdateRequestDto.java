package com.newsfeed.testagram.domain.member.dto.request;


import lombok.Getter;

/**
 * 로그인한 사용자의 프로필 수정 요청 DTO입니다.
 */
@Getter
public class MyProfileUpdateRequestDto {
    private String nickname;
    private String image;
}
