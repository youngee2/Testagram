package com.newsfeed.testagram.domain.member.dto;

import lombok.Getter;

@Getter
public class MemberSignUpRequest {
    private String email;
    private String password;
    private String memberName;
}
