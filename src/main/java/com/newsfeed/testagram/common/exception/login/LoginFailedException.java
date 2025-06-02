package com.newsfeed.testagram.common.exception.login;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LoginFailedException extends RuntimeException {
    public LoginFailedException(String message) {
        super(message);
    }
    public LoginFailedException() {
        super("로그인에 실패했습니다.");
    }

    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
