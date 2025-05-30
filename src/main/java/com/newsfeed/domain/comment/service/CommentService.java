package com.newsfeed.domain.comment.service;

import com.newsfeed.domain.comment.dto.CommentRequest;
import com.newsfeed.domain.comment.dto.CommentResponse;
import com.newsfeed.domain.comment.entity.Comment;
import com.newsfeed.domain.comment.exception.CommentNotFoundException;
import com.newsfeed.domain.comment.repository.CommentRepository;
import com.newsfeed.domain.post.entity.Post;
import com.newsfeed.domain.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void createComment(CommentRequest request, Long writerId) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .post(post)
                .writer(post.getWriter())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        commentRepository.save(comment);
    }

    public List<CommentResponse> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(CommentResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateComment(Long commentId, CommentRequest request, Long requesterId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow();

        if (!comment.getPost().getWriter().getId().equals(requesterId)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        comment.updateContent(request.getContent());
    }

    @Transactional
    public void deleteComment(Long commentId, Long requesterId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow();

        // 댓글 작성자나 게시글 작성자만 삭제 가능
        Long postWriterId = comment.getPost().getWriter().getId();
        if (!comment.getPost().getWriter().getId().equals(requesterId)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }

        commentRepository.delete(comment);
    }
}
