package com.newsfeed.testagram.domain.follow.dto.follow;

import com.newsfeed.testagram.domain.member.entity.Member;
import com.newsfeed.testagram.domain.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FollowingMemberPostResponseDto {
    private Long postId;
    private Long writerId;
    private String writerNickname;
    private String writerEmail;
    private String content;
    private LocalDateTime createdAt;

    // Optional fields
    private int likeCount;

    public FollowingMemberPostResponseDto(Long postId, Long writerId, String writerNickname, String writerEmail, String content, LocalDateTime createdAt) {
        this.postId = postId;
        this.writerId = writerId;
        this.writerNickname = writerNickname;
        this.writerEmail = writerEmail;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static FollowingMemberPostResponseDto tofollowingMemberPostResponseDto(Post post, Member member) {
        return new FollowingMemberPostResponseDto(
                post.getId(),
                member.getId(),
                member.getNickname(),
                member.getEmail(),
                post.getContent(),
                post.getCreatedAt()
        );
    }
}
