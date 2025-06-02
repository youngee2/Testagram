package com.newsfeed.testagram.common.exception;

import com.newsfeed.testagram.common.exception.dto.ErrorResponse;
import com.newsfeed.testagram.common.exception.file.FileUploadException;
import com.newsfeed.testagram.common.exception.file.UnsupportedImageFormatException;
import com.newsfeed.testagram.common.exception.login.IncorrectPasswordException;
import com.newsfeed.testagram.common.exception.login.LoginFailedException;
import com.newsfeed.testagram.common.exception.member.*;
import com.newsfeed.testagram.common.exception.post.PostNotFoundException;
import com.newsfeed.testagram.domain.comment.exception.CommentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleMemberNotFoundException(MemberNotFoundException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "MEMBER_NOT_FOUND");
        error.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(IncorrectPasswordException e) {
        return ResponseEntity
                .status(e.getStatus().value())
                .body(new ErrorResponse(e.getStatus().value(), e.getMessage()));
    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(LoginFailedException e) {
        return ResponseEntity
                .status(e.getStatus().value())
                .body(new ErrorResponse(e.getStatus().value(), e.getMessage()));
    }

    // 409: 같은 비밀번호
    @ExceptionHandler(SamePasswordException.class)
    public ResponseEntity<String> handleSamePassword(SamePasswordException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(EmailAlreadyExistsException e) {
        return ResponseEntity
                .status(e.getStatus().value())
                .body(new ErrorResponse(e.getStatus().value(), e.getMessage()));
    }

    // 400: 비밀번호 형식 오류
    @ExceptionHandler(InvalidPasswordFormatException.class)
    public ResponseEntity<String> handleInvalidPasswordFormat(InvalidPasswordFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(EmailNotFoundException e) {
        return ResponseEntity
                .status(e.getStatus().value())
                .body(new ErrorResponse(e.getStatus().value(), e.getMessage()));
    }

    // 403: 이미 탈퇴한 사용자
    @ExceptionHandler(AlreadyWithdrawnMemberException.class)
    public ResponseEntity<String> handleAlreadyWithdrawn(AlreadyWithdrawnMemberException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = (fieldError != null) ? fieldError.getDefaultMessage() : "유효성 검사 실패";
        return ResponseEntity
                .status(e.getStatusCode().value())
                .body(new ErrorResponse(e.getStatusCode().value(), message));
    }
    @ExceptionHandler(UnsupportedImageFormatException.class)
    public ResponseEntity<String> handleUnsupportedImageFormat(UnsupportedImageFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    // 500: 파일 업로드 실패
    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<String> handleFileUpload(FileUploadException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePostNotFound(PostNotFoundException e){
        return ResponseEntity
                .status(e.getStatus().value())
                .body(new ErrorResponse(e.getStatus().value(), e.getMessage()));
    }
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCommentNotFound(CommentNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "COMMENT_NOT_FOUND");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    // 기타 예외 처리 예시
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllExceptions(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "INTERNAL_SERVER_ERROR");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
