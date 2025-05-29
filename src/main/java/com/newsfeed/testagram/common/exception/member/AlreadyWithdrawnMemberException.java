package com.newsfeed.testagram.common.exception.member;

/**
 *이미 탈퇴한 사용자가 접근하려 할 때 발생하는 예외입니다.
 * soft delete된 사용자 계정이 인증 요청이나 리소스 접근을 시도할 경우 발생합니다.
 * 예) 탈퇴 후 재로그인 시도, 정보 수정 요청 등
 */

public class AlreadyWithdrawnMemberException extends RuntimeException {

    public AlreadyWithdrawnMemberException() {
        super("이미 탈퇴한 사용자입니다.");
    }

    public AlreadyWithdrawnMemberException(String message) {
        super(message);
    }
}
