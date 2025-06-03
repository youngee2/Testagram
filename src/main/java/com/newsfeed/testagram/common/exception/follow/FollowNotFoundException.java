package com.newsfeed.testagram.common.exception.follow;

import org.springframework.http.HttpStatus;

public class FollowNotFoundException extends FollowException {
    public FollowNotFoundException() {
        super("팔로우 관계가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
    }
}
