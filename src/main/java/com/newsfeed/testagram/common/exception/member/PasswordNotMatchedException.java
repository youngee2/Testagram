package com.newsfeed.testagram.common.exception.member;

import org.springframework.http.HttpStatus;

/**
 * 사용자가 입력한 현재 비밀번호가 실제 비밀번호와 일치하지 않을 때 발생하는 예외입니다.
 *
 * <p>예: 비밀번호 변경 또는 회원 탈퇴 시,
 * 입력된 기존 비밀번호가 DB에 저장된 값과 다를 경우 발생합니다.</p>
 *
 * <p>보안을 위해 민감한 작업 전 사용자 본인임을 검증하는 절차로 사용됩니다.</p>
 *
 */
public class PasswordNotMatchedException extends RuntimeException {

    public PasswordNotMatchedException() {
        super("현재 비밀번호가 일치하지 않습니다.");
    }

    public PasswordNotMatchedException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
