package com.newsfeed.testagram.domain.post.dto.request;

import lombok.Getter;

@Getter
public class PostUpdateRequestDto {
    private Long postId;
    private String content;
}
