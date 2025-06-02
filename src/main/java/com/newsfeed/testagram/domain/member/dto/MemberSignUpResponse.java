package com.newsfeed.testagram.domain.member.dto;

import com.newsfeed.testagram.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSignUpResponse {

    private String message;
    private long id;

    public static MemberSignUpResponse toDto(String message, Member member) {
        return new MemberSignUpResponse(message, member.getId());
    }
}
