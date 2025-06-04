package com.newsfeed.testagram.domain.post.dto.response;

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
