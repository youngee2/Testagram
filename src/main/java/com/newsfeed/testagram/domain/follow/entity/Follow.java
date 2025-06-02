package com.newsfeed.testagram.domain.follow.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "Follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "follower_id", nullable = false)
    private Long followerId;

    @Column(name = "following_id", nullable = false)
    private Long followingId;

    @Column(nullable = false)
    private LocalDateTime followedAt;

    public Follow() {}

    public Follow(Long followerId, Long followingId, LocalDateTime followedAt) {
        this.followerId = followerId;
        this.followingId = followingId;
        this.followedAt = followedAt;
    }
}
