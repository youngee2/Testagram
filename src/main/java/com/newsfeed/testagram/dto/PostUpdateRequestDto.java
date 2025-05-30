package com.newsfeed.testagram.dto;

import lombok.Getter;

@Getter
public class PostUpdateRequestDto {
    private Long postId;
    private String content;
}
