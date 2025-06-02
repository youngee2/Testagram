package com.newsfeed.testagram.common.exception.member;

/**
 * JWT 토큰이 없거나 유효하지 않을 때 발생하는 예외입니다.
 *
 * <p>로그인하지 않은 사용자이거나, 만료되었거나 위조된 토큰으로 접근 시 발생합니다.</p>
 *
 * <p>주로 다음과 같은 상황에서 사용됩니다:</p>
 * <ul>
 *     <li>Authorization 헤더에 JWT 토큰이 누락된 경우</li>
 *     <li>토큰의 서명이 잘못되었거나 만료된 경우</li>
 *     <li>SecurityContext에 사용자 정보가 없는 경우</li>
 * </ul>
 *
 * <p>보안이 요구되는 API 요청은 이 예외를 통해 접근을 제한합니다.</p>
 */
public class UnauthorizedAccessException extends RuntimeException {

    public UnauthorizedAccessException() {
        super("로그인 해주세요.");
    }

    public UnauthorizedAccessException(String message) {
        super(message);
    }

}
