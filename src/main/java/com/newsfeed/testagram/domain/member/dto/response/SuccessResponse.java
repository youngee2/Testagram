package com.newsfeed.testagram.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class SuccessResponse {
    private String message;
}
