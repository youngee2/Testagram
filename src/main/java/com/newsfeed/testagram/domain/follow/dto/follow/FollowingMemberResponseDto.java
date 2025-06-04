package com.newsfeed.testagram.domain.follow.dto.follow;

import com.newsfeed.testagram.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class FollowingMemberResponseDto {

    private Long followingMemberId;
    private String followingMemberNickname;
    private String followingMemberEmail;

    public FollowingMemberResponseDto(Long followingMemberId, String followingMemberNickname, String followingMemberEmail) {
        this.followingMemberId = followingMemberId;
        this.followingMemberNickname = followingMemberNickname;
        this.followingMemberEmail = followingMemberEmail;
    }

    public static FollowingMemberResponseDto toFollowingMemberResponseDto(Member member) {
        return new FollowingMemberResponseDto(
                member.getId(),
                member.getNickname(),
                member.getEmail()
        );
    }
}
