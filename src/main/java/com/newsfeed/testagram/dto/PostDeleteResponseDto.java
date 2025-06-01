package com.newsfeed.testagram.dto;

public class PostDeleteResponseDto {
    private Integer status;
    private String message;

    public PostDeleteResponseDto(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

