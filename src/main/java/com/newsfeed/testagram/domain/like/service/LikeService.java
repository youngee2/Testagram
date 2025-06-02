package com.newsfeed.testagram.domain.like.service;

import com.newsfeed.testagram.domain.comment.entity.Comment;
import com.newsfeed.testagram.domain.like.entity.CommentLike;
import com.newsfeed.testagram.domain.like.entity.PostLike;
import com.newsfeed.testagram.domain.like.repository.CommentLikeRepository;
import com.newsfeed.testagram.domain.like.repository.PostLikeRepository;
import com.newsfeed.testagram.domain.member.entity.Member;
//import com.newsfeed.domain.member.repository.MemberRepository;
import com.newsfeed.testagram.domain.member.repository.MemberRepository;
import com.newsfeed.testagram.domain.post.entity.Post;
import com.newsfeed.testagram.domain.comment.repository.CommentRepository;
import com.newsfeed.testagram.domain.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    // 게시글 좋아요
    @Transactional
    public void likePost(Long postId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Post post = getPost(postId);

        // 자기 게시글 좋아요 금지
        if (post.getWriter().getId().equals(memberId)) {
            throw new IllegalArgumentException("본인의 게시글에는 좋아요를 할 수 없습니다.");
        }

        if (postLikeRepository.existsByMemberAndPost(member, post)) {
            throw new IllegalArgumentException("이미 좋아요를 누른 게시글입니다.");
        }

        PostLike postLike = PostLike.builder()
                .member(member)
                .post(post)
                .likedAt(LocalDateTime.now())
                .build();

        postLikeRepository.save(postLike);
    }

    // 게시글 좋아요 취소
    @Transactional
    public void unlikePost(Long postId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Post post = getPost(postId);

        PostLike postLike = postLikeRepository.findByMemberAndPost(member, post)
                .orElseThrow(() -> new IllegalArgumentException("좋아요를 누르지 않은 게시글입니다."));

        postLikeRepository.delete(postLike);
    }

    // 댓글 좋아요
    @Transactional
    public void likeComment(Long commentId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Comment comment = getComment(commentId);

        if (comment.getWriter().getId().equals(memberId)) {
            throw new IllegalArgumentException("본인의 댓글에는 좋아요를 할 수 없습니다.");
        }

        if (commentLikeRepository.existsByMemberAndComment(member, comment)) {
            throw new IllegalArgumentException("이미 좋아요를 누른 댓글입니다.");
        }

        CommentLike commentLike = CommentLike.builder()
                .member(member)
                .comment(comment)
                .likedAt(LocalDateTime.now())
                .build();

        commentLikeRepository.save(commentLike);
    }

    // 댓글 좋아요 취소
    @Transactional
    public void unlikeComment(Long commentId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Comment comment = getComment(commentId);

        CommentLike commentLike = commentLikeRepository.findByMemberAndComment(member, comment)
                .orElseThrow(() -> new IllegalArgumentException("좋아요를 누르지 않은 댓글입니다."));

        commentLikeRepository.delete(commentLike);
    }

    //댓글 좋아요 개수 불러오기
    public Map<Long, Long> getLikeCountForComments(List<Long> commentIds) {
        List<Object[]> results = commentLikeRepository.countLikesByCommentIds(commentIds);
        Map<Long, Long> likeCountMap = new HashMap<>();

        for (Object[] row : results) {
            Long commentId = (Long) row[0];
            Long likeCount = (Long) row[1];
            likeCountMap.put(commentId, likeCount);
        }

        return likeCountMap;
    }

    // ====== private ======


    private Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }

    private Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    private Comment getComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
    }
}
