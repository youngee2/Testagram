package com.newsfeed.testagram.dto;

import com.newsfeed.testagram.service.CreatePostService;
import lombok.Getter;

@Getter
public class CreatePostResponseDto {


    private final int status;

    private final String message;

    //생성자
    public CreatePostResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
