package com.newsfeed.testagram.member.dto.request;


import lombok.Getter;

/**
 * 비밀번호 변경을 위한 요청 DTO입니다.
 */
@Getter
public class PasswordRequestDto {
    private String currentPassword;
    private String newPassword;
}
