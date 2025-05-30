package com.newsfeed.testagram.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor
public class PostUpdateResponseDto {
    private int status;
    private String message;

}
