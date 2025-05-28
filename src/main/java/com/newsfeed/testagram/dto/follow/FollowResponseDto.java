package com.newsfeed.testagram.dto.follow;

import lombok.Getter;

@Getter
public class FollowResponseDto {
    private Long followerId;
    private Long followingId;
    private String followingEmail;

    public FollowResponseDto(Long followerId, Long followingId, String followingEmail) {
        this.followerId = followerId;
        this.followingId = followingId;
        this.followingEmail = followingEmail;
    }
}
