package com.newsfeed.testagram.common.exception.member;

public class EmailNotFoundException extends RuntimeException {
  public EmailNotFoundException(String message) {
    super(message);
  }
  public EmailNotFoundException() {
    super("이메일을 찾지못했습니다.");
  }
}
