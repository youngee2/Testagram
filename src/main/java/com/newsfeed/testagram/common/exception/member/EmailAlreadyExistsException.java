package com.newsfeed.testagram.common.exception.member;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
    public EmailAlreadyExistsException() {
        super("이미 사용중인 이메일 입니다.");
    }
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }

}
