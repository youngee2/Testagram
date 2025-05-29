package com.newsfeed.testagram.common.exception.member;

/**
 * <p>로그인 사용자와 요청 대상 사용자가 일치하지 않을 때 발생하는 예외입니다.</p>
 * <p>예) 특정 회원의 정보를 수정 또는 삭제하려는 요청이 현재 로그인한 사용자와 동일하지 않는 경우</p>
 */
public class MemberNotMatchedException extends RuntimeException {

    public MemberNotMatchedException() {
        super("로그인한 사용자와 요청 대상 사용자가 일치하지 않습니다.");
    }
    public MemberNotMatchedException(String message) {
        super(message);
    }

}
