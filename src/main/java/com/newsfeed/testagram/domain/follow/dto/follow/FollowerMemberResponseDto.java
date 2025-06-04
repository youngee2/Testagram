package com.newsfeed.testagram.domain.follow.dto.follow;

import com.newsfeed.testagram.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class FollowerMemberResponseDto {

    private Long followerMemberId;
    private String followerMemberNickname;
    private String followerMemberEmail;

    public FollowerMemberResponseDto(Long followerMemberId, String followerMemberNickname, String followerMemberEmail) {
        this.followerMemberId = followerMemberId;
        this.followerMemberNickname = followerMemberNickname;
        this.followerMemberEmail = followerMemberEmail;
    }

    public static FollowerMemberResponseDto toFollowerMemberResponseDto(Member member) {
        return new FollowerMemberResponseDto(
                member.getId(),
                member.getNickname(),
                member.getEmail()
        );
    }
}
