package com.newsfeed.testagram.common.exception.like;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LikeException extends RuntimeException {

    private final HttpStatus status;

    public LikeException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
