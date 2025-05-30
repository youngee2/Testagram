package com.newsfeed.testagram.dto.post;

import com.newsfeed.testagram.entity.Member;
import com.newsfeed.testagram.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SearchPostResponseDto {

    private Long postId;
    private Long writerId;
    private String writerNickname;
    private String writerEmail;
    private String content;
    private LocalDateTime createdAt;

    public SearchPostResponseDto(Long postId, Long writerId, String writerNickname, String writerEmail, String content, LocalDateTime createdAt) {
        this.postId = postId;
        this.writerId = writerId;
        this.writerNickname = writerNickname;
        this.writerEmail = writerEmail;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static SearchPostResponseDto toSearchPostResponseDto(Post post, Member member) {
        return new SearchPostResponseDto(
                post.getId(),
                member.getId(),
                member.getNickname(),
                member.getEmail(),
                post.getContent(),
                post.getCreatedAt()
        );
    }
}
