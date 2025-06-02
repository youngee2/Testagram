package com.newsfeed.domain.like.repository;

import com.newsfeed.domain.like.entity.PostLike;
import com.newsfeed.domain.member.entity.Member;
import com.newsfeed.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    boolean existsByMemberAndPost(Member member, Post post);

    Optional<PostLike> findByMemberAndPost(Member member, Post post);
}
