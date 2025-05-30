package com.newsfeed.testagram.member.dto.request;


import lombok.Getter;

@Getter
public class PasswordRequestDto {
    private String currentPassword;
    private String newPassword;
}
