package com.newsfeed.testagram.common.exception.member;

/**
 *비밀번호 형식이 올바르지 않을 때 발생하는 예외입니다.
 *
 * 비밀번호는 다음 조건을 만족해야 합니다.
 * <ul>
 *     <li>대소문자 포함 영문+숫자+특수문자를 최소 1글자씩 포함합니다.</li>
 *     <li>비밀번호는 최소 8글자 이상이어야 합니다.</li>
 * </ul>
 * 형식을 지키지 않으면 비밀번호 변경 요청이 거절됩니다.
 */
public class InvalidPasswordFormatException extends RuntimeException{
    public InvalidPasswordFormatException() {
        super("비밀번호 형식이 올바르지 않습니다. 영문+숫자+특수문자 포함 최소 8자 이상이어야 합니다.");
    }

    public InvalidPasswordFormatException(String message) {
        super(message);
    }
}
