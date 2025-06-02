package com.newsfeed.testagram.domain.post.dto.request;

import lombok.Getter;

@Getter
public class PostDetailRequestDto {
    private Long postId;

    public PostDetailRequestDto(Long postId){
    this.postId = postId;
    }
}
