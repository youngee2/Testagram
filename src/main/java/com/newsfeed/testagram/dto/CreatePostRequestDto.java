package com.newsfeed.testagram.dto;

import lombok.Getter;

@Getter
public class CreatePostRequestDto {

    private String content;

    public CreatePostRequestDto(String content) {
        this.content = content;
    }
}




