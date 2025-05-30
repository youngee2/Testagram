package com.newsfeed.testagram.dto;

import lombok.Getter;

@Getter
public class CreatePostRequestDto {
    //임시
    private Long writerId;

    private String content;

//    public CreatePostRequestDto(String content) {
//        this.content = content;
//    }

    //임시
    public CreatePostRequestDto(Long writerId, String content) {
        this.content = content;
        this.writerId = writerId;
    }
}




