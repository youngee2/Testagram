package com.newsfeed.testagram.domain.comment.controller;

import com.newsfeed.testagram.common.util.JwtUtil;
import com.newsfeed.testagram.domain.comment.dto.CommentRequest;
import com.newsfeed.testagram.domain.comment.dto.CommentResponse;
import com.newsfeed.testagram.domain.comment.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;
    private final JwtUtil jwtUtil;


    //헤더에서 토큰 추출하기
    private Long extractMemberId(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || !bearerToken.startsWith(JwtUtil.BEARER_PREFIX)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
        String token = bearerToken.substring(JwtUtil.BEARER_PREFIX.length());
        return jwtUtil.getMemberIdFromToken(token);
    }


    // 댓글 작성
    @PostMapping("/comments")
    public ResponseEntity<Map<String, Long>> createComment(@RequestBody @Valid CommentRequest request,
                                                           HttpServletRequest httpRequest) {

        //Long writerId = 1L; // 임시 처리

        Long writerId = extractMemberId(httpRequest);
        commentService.createComment(request, writerId);
        return ResponseEntity.ok().build();
    }

    // 댓글 수정
    @PutMapping("/comments/{id}")
    public ResponseEntity<Map<String, Long>> updateComment(@PathVariable Long id,
                                                           @RequestBody @Valid CommentRequest request,
                                                           HttpServletRequest httpRequest) {
        //Long requesterId = 1L; // 임시 처리

        Long requesterId = extractMemberId(httpRequest);
        commentService.updateComment(id, request, requesterId);
        return ResponseEntity.ok().build();
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id,
                                              HttpServletRequest httpRequest) {

        //Long requesterId = 1L; // 임시 처리

        Long requesterId = extractMemberId(httpRequest);
        commentService.deleteComment(id, requesterId);

        return ResponseEntity.ok().build();
    }

    // 게시글의 댓글 목록 조회
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByPost(@PathVariable Long postId) {
        List<CommentResponse> response = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(response);
    }
}
