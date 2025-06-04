package com.newsfeed.testagram.common.exception.post;

import org.springframework.http.HttpStatus;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message) {
        super(message);
    }
    public PostNotFoundException() {
        super("게시글을 찾을 수 없습니다.");
    }

    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
