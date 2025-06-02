package com.newsfeed.testagram.domain.comment.dto;

import com.newsfeed.testagram.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponse {

    private Long id;
    private Long postId;
    private Long writerId;
    private String writerNickname;
    private String content;
    private String likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CommentResponse fromEntity(Comment comment, Long likeCount) {
        return CommentResponse.builder()
                .id(comment.getId())
                .postId(comment.getPost().getId())
                .writerId(comment.getWriter().getId())
                .writerNickname(comment.getWriter().getNickname())
                .content(comment.getContent())
                .likeCount(likeCount.toString())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}
