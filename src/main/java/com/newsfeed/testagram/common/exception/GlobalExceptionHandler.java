package com.newsfeed.testagram.common.exception;

import com.newsfeed.testagram.common.exception.dto.ErrorResponse;
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
    public ResponseEntity<ErrorResponse> handleCustomException(MemberNotFoundException e) {
        return ResponseEntity
                .status(e.getStatus().value())
                .body(new ErrorResponse(e.getStatus().value(), e.getMessage()));
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

    @ExceptionHandler(SamePasswordException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(SamePasswordException e) {
        return ResponseEntity
                .status(e.getStatus().value())
                .body(new ErrorResponse(e.getStatus().value(), e.getMessage()));
    }

    @ExceptionHandler(InvalidPasswordFormatException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(InvalidPasswordFormatException e) {
        return ResponseEntity
                .status(e.getStatus().value())
                .body(new ErrorResponse(e.getStatus().value(), e.getMessage()));
    }
    @ExceptionHandler(PasswordNotMatchedException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(PasswordNotMatchedException e) {
        return ResponseEntity
                .status(e.getStatus().value())
                .body(new ErrorResponse(e.getStatus().value(), e.getMessage()));
    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(EmailAlreadyExistsException e) {
        return ResponseEntity
                .status(e.getStatus().value())
                .body(new ErrorResponse(e.getStatus().value(), e.getMessage()));
    }



    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(EmailNotFoundException e) {
        return ResponseEntity
                .status(e.getStatus().value())
                .body(new ErrorResponse(e.getStatus().value(), e.getMessage()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = (fieldError != null) ? fieldError.getDefaultMessage() : "유효성 검사 실패";
        return ResponseEntity
                .status(e.getStatusCode().value())
                .body(new ErrorResponse(e.getStatusCode().value(), message));
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
