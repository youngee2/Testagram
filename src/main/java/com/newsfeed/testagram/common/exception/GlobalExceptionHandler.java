package com.newsfeed.testagram.common.exception;

import com.newsfeed.testagram.common.exception.file.FileUploadException;
import com.newsfeed.testagram.common.exception.file.UnsupportedImageFormatException;
import com.newsfeed.testagram.common.exception.member.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404: 회원 없음
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<String> handleMemberNotFound(MemberNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    // 403: 사용자 불일치
    @ExceptionHandler(MemberNotMatchedException.class)
    public ResponseEntity<String> handleMemberNotMatched(MemberNotMatchedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    // 401: 비밀번호 불일치
    @ExceptionHandler(PasswordNotMatchedException.class)
    public ResponseEntity<String> handlePasswordNotMatched(PasswordNotMatchedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    // 409: 같은 비밀번호
    @ExceptionHandler(SamePasswordException.class)
    public ResponseEntity<String> handleSamePassword(SamePasswordException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    // 400: 비밀번호 형식 오류
    @ExceptionHandler(InvalidPasswordFormatException.class)
    public ResponseEntity<String> handleInvalidPasswordFormat(InvalidPasswordFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


    // 403: 이미 탈퇴한 사용자
    @ExceptionHandler(AlreadyWithdrawnMemberException.class)
    public ResponseEntity<String> handleAlreadyWithdrawn(AlreadyWithdrawnMemberException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    // 401: JWT 오류
    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<String> handleUnauthorized(UnauthorizedAccessException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    // 400: 이미지 포맷 오류
    @ExceptionHandler(UnsupportedImageFormatException.class)
    public ResponseEntity<String> handleUnsupportedImageFormat(UnsupportedImageFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    // 500: 파일 업로드 실패
    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<String> handleFileUpload(FileUploadException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    // 500: 일반 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("서버 오류가 발생했습니다: " + e.getMessage());
    }
}
