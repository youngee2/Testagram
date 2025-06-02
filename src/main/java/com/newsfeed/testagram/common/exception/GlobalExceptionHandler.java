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
    /**
     * 사용자가 잘못된 비밀번호를 입력할 경우 발생합니다.
     * 패스워드 불일치
     * 예시 메시지: "비밀번호가 일치하지 않습니다."
     *
     * @param e IncorrectPasswordException 예외 객체
     * @return 상태 코드와 에러 메시지가 담긴 ResponseEntity
     */

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectPasswordException(IncorrectPasswordException e) {
        return ResponseEntity
                .status(e.getStatus().value())
                .body(new ErrorResponse(e.getStatus().value(), e.getMessage()));
    }

    /**
     * 사용자가 로그인에 실패했을 경우 발생합니다.
     * 로그인 실패 (아이디 또는 비밀번호 불일치, 존재하지 않는 사용자 등)
     * 예시 메시지: "로그인에 실패하였습니다."
     *
     * @param e LoginFailedException 예외 객체
     * @return 상태 코드와 에러 메시지가 담긴 ResponseEntity
     */
    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ErrorResponse> handleLoginFailedException(LoginFailedException e) {
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

    /**
     * 회원가입 시 이미 존재하는 이메일을 입력한 경우 발생합니다.
     * 이메일 중복
     * 예시 메시지: "이미 사용 중인 이메일입니다."
     *
     * @param e EmailAlreadyExistsException 예외 객체
     * @return 상태 코드와 에러 메시지가 담긴 ResponseEntity
     */

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        return ResponseEntity
                .status(e.getStatus().value())
                .body(new ErrorResponse(e.getStatus().value(), e.getMessage()));
    }


    /**
     * 입력한 이메일이 시스템에 존재하지 않을 경우 발생합니다.
     * 이메일 미존재
     * 예시 메시지: "존재하지 않는 이메일입니다."
     *
     * @param e EmailNotFoundException 예외 객체
     * @return 상태 코드와 에러 메시지가 담긴 ResponseEntity
     */

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmailNotFoundException(EmailNotFoundException e) {
        return ResponseEntity
                .status(e.getStatus().value())
                .body(new ErrorResponse(e.getStatus().value(), e.getMessage()));
    }


    /**
     * 요청 데이터의 유효성 검사에 실패한 경우 발생합니다.
     * 요청값 유효성 검증 실패 (ex. @NotBlank, @Email 등)
     * 예시 메시지: "이메일 형식이 올바르지 않습니다.", "비밀번호는 필수 입력값입니다." 등
     *
     * @param e MethodArgumentNotValidException 예외 객체
     * @return 상태 코드와 에러 메시지가 담긴 ResponseEntity
     */

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

    // 댓글 없음 예외 처리
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
