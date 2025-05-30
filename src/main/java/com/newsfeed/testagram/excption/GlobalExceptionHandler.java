package com.newsfeed.testagram.excption;

import com.newsfeed.testagram.dto.ResponseDto;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 공통 RuntimeException 처리
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDto<?>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDto.fail(500, "서버 내부 오류", ex.getMessage()));
    }

    // IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto<?>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDto.fail(400, "잘못된 요청", ex.getMessage()));
    }

    // ID 등 경로변수 타입 에러
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseDto<?>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDto.fail(400, "잘못된 매개변수 타입", ex.getMessage()));
    }

    // 사용자 정의 예외 예시
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ResponseDto<?>> handleNotFound(ChangeSetPersister.NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseDto.fail(404, ex.getMessage(), "리소스를 찾을 수 없습니다."));
    }

    // 기타 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<?>> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDto.fail(500, "알 수 없는 오류", ex.getMessage()));
    }
}

