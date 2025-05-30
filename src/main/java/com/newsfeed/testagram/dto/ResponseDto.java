package com.newsfeed.testagram.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto <T>{
    //성공 여부
    private boolean success;
    //응답 데이터
    private T data;
    //실패 시 에러 정보
    private Error error;

    //성공 응답
    public static <T> ResponseDto<T> success(T data){
        return new ResponseDto<>(true,data,null);
    }
    //실패 응답
    public static <T> ResponseDto<T> fail(Integer httpStatus, String message, String detail){
        return new ResponseDto<>(false,null, new Error(httpStatus, message, detail));
    }
    // 에러 내부 클래스

    @Getter
    @AllArgsConstructor
    public static class Error{
        //예외에 대한 Http 상태 코드
        private Integer httpStatus;
        //사용자에게 보여주는 메세지
        private String message;
        //개발자용 상태 메시지 (디버깅용)
        private String detail;
    }
}
