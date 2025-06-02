package com.newsfeed.testagram.domain.follow.dto.follow;

import lombok.Getter;

@Getter
public class FollowRequestDTO {

    private final Long targetMemberId;

    public FollowRequestDTO(Long targetMemberId) {
        this.targetMemberId = targetMemberId;
    }
}
