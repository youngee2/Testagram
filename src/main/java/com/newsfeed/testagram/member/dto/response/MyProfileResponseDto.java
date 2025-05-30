package com.newsfeed.testagram.member.dto.response;

import com.newsfeed.testagram.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyProfileResponseDto {
    private Long id;
    private String email;
    private String nickname;
    private String image;
    private LocalDateTime updateAt;

    public static MyProfileResponseDto of(Member member) {
        return new MyProfileResponseDto(
                member.getId(),
                member.getEmail(),
                member.getImage(),
                member.getNickname(),
                member.getCreatedAt()
        );
    }
}
