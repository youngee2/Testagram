package com.newsfeed.testagram.domain.comment.repository;

import com.newsfeed.testagram.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);

    boolean existsByIdAndWriterId(Long commentId, Long writerId);
}
