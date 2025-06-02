package com.newsfeed.testagram.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {

    private Long postId;

    @NotBlank(message = "댓글 내용은 필수입니다.")
    private String content;
}
