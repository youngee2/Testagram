package com.newsfeed.testagram.dto.follow;

import com.newsfeed.testagram.entity.Member;
import lombok.Getter;

@Getter
public class FollowingMemberResponseDto {

    private Long followingMemberId;
    private String followingMemberNickname;
    private String followingMemeberEmail;

    public FollowingMemberResponseDto(Long followingMemberId, String followingMemberNickname, String followingMemeberEmail) {
        this.followingMemberId = followingMemberId;
        this.followingMemberNickname = followingMemberNickname;
        this.followingMemeberEmail = followingMemeberEmail;
    }

    public static FollowingMemberResponseDto toFollowingMemberResponseDto(Member member) {
        return new FollowingMemberResponseDto(
                member.getId(),
                member.getNickname(),
                member.getEmail()
        );
    }
}
