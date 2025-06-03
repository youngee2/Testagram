package com.newsfeed.testagram.common.exception.follow;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FollowException extends RuntimeException {
    private final HttpStatus status;

    public FollowException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
