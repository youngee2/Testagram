package com.newsfeed.testagram.member.dto.response;


import com.newsfeed.testagram.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyProfileUpdateResponseDto {
    private String nickname;
    private String image;


    public static MyProfileUpdateResponseDto of(Member member) {
        return new MyProfileUpdateResponseDto(
                member.getNickname(),
                member.getImage()
        );
    }
}
