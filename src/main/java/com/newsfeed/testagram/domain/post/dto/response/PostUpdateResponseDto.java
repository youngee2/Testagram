package com.newsfeed.testagram.domain.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUpdateResponseDto {
    private int status;
    private String message;

}
