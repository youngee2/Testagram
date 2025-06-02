package com.newsfeed.domain.like.repository;

import com.newsfeed.domain.comment.entity.Comment;
import com.newsfeed.domain.like.entity.CommentLike;
import com.newsfeed.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    boolean existsByMemberAndComment(Member member, Comment comment);

    Optional<CommentLike> findByMemberAndComment(Member member, Comment comment);

    @Query("SELECT cl.comment.id AS commentId, COUNT(cl) AS likeCount " +
            "FROM CommentLike cl WHERE cl.comment.id IN :commentIds " +
            "GROUP BY cl.comment.id")
    List<Object[]> countLikesByCommentIds(@Param("commentIds") List<Long> commentIds);
}
