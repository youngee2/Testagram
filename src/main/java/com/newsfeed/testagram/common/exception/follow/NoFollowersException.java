package com.newsfeed.testagram.common.exception.follow;

import org.springframework.http.HttpStatus;

public class NoFollowersException extends FollowException {
    public NoFollowersException() {
        super("팔로잉한 멤버가 없습니다.", HttpStatus.NOT_FOUND);
    }
}
