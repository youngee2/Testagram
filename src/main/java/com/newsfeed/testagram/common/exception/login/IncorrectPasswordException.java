package com.newsfeed.testagram.common.exception.login;

import org.springframework.http.HttpStatus;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
    public IncorrectPasswordException() {
        super("패스워드가 불일치");
    }
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
