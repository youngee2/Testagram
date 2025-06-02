package com.newsfeed.testagram.domain.post.dto.request;

import lombok.Getter;

@Getter
public class CreatePostRequestDto {

    private String content;

//    public CreatePostRequestDto(String content) {
//        this.content = content;
//    }

    //임시
    public CreatePostRequestDto(Long writerId, String content) {
        this.content = content;
    }
}




