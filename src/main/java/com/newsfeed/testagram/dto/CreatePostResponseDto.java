package com.newsfeed.testagram.dto;

import lombok.Getter;

@Getter
public class CreatePostResponseDto {


    private int status;

    private String message;

    //생성자
    public CreatePostResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
