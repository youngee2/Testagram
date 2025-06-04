package com.newsfeed.testagram.domain.follow.dto.follow;

import lombok.Getter;

@Getter
public class FollowResponseDto {
    private Long followerId;
    private Long followingId;
    private String followingNickName;
    private String followingEmail;

    public FollowResponseDto(Long followerId, Long followingId, String followingNickName, String followingEmail) {
        this.followerId = followerId;
        this.followingId = followingId;
        this.followingNickName = followingNickName;
        this.followingEmail = followingEmail;
    }
}
