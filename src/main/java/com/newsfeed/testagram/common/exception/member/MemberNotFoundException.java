package com.newsfeed.testagram.common.exception.member;

/**
 * 회원 정보가 존재하지 않을 때 발생하는 예외입니다.
 * 주로 다음 상황에서 사용됩니다.
 * <ul>
 *     <li>회원 조회 API</li>
 *     <li>탈퇴 여부 확인</li>
 * </ul>
 */
public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException() {
        super("회원 정보가 존재하지 않습니다.");
    }

    public MemberNotFoundException(String message) {
        super(message);
    }
}
