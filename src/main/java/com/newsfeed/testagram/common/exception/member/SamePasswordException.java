package com.newsfeed.testagram.common.exception.member;

import org.springframework.http.HttpStatus;

/**
 * 새 비밀번호가 기존 비밀번호와 동일할 때 발생하는 예외입니다.
 *
 * <p>비밀번호 변경 시, 기존 비밀번호와 다른 값을 입력해야 하는 정책을 위반했을 경우에 사용됩니다.</p>
 *
 * <p>보안성과 사용자 경험을 위해 같은 비밀번호로의 변경은 허용되지 않습니다.</p>
 *
 * <p>예: 사용자가 비밀번호 변경을 요청했지만 현재 비밀번호와 동일한 값을 입력한 경우</p>
 */
public class SamePasswordException extends RuntimeException {

    public SamePasswordException() {
        super("새 비밀번호는 기존 비밀번호와 달라야 합니다.");
    }

    public SamePasswordException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
