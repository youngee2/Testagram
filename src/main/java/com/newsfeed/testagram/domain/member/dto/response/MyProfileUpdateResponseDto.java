package com.newsfeed.testagram.domain.member.dto.response;


import com.newsfeed.testagram.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 로그인한 사용자의 수정된 정보 응답 DTO입니다.
 */
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
