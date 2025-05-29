package com.newsfeed.testagram.common.exception.member;

public class MemberNotFoundException extends RuntimeException {
  public MemberNotFoundException(String message) {
    super(message);
  }
  public MemberNotFoundException() {
    super("사용자를 찾지못했습니다.");
  }
}
