package com.newsfeed.testagram.dto;

import lombok.Getter;

@Getter
public class PostDetailRequestDto {
    private Long postId;

    public PostDetailRequestDto(Long postId){
    this.postId = postId;
    }
}
