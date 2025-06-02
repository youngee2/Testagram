package com.newsfeed.testagram.domain.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostDetailResponseDto {
    private int status;
    private String message;
    private String nickName;
    private String content;
    private long likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
