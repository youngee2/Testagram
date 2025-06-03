package com.newsfeed.testagram.common.exception.follow;

import org.springframework.http.HttpStatus;

public class NoFollowingMembersException extends FollowException {
    public NoFollowingMembersException() {
        super("팔로우한 멤버가 없습니다.", HttpStatus.NOT_FOUND);
    }
}
