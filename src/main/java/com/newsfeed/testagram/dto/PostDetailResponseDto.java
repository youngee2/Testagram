package com.newsfeed.testagram.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostDetailResponseDto {
    private int status;
    private String message;
    private String nickName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
