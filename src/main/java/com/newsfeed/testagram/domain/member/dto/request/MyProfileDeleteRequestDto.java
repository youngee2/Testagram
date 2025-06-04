package com.newsfeed.testagram.domain.member.dto.request;

import lombok.Getter;

/**
 * 회원 탈퇴 시 비밀번호를 확인받기 위한 요청 DTO입니다.
 */
@Getter
public class MyProfileDeleteRequestDto {
    private String password;
}
