package com.newsfeed.testagram.domain.post.dto.response;

import lombok.Getter;

@Getter
public class PostDeleteResponseDto {
    private final Integer status;
    private final String message;

    public PostDeleteResponseDto(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

}

