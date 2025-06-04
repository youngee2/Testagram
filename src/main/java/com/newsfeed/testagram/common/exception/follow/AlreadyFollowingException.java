package com.newsfeed.testagram.common.exception.follow;

import org.springframework.http.HttpStatus;

public class AlreadyFollowingException extends RuntimeException {

    private final HttpStatus status;

    public AlreadyFollowingException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}