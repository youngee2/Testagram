package com.newsfeed.domain.comment.controller;

import com.newsfeed.domain.comment.dto.CommentRequest;
import com.newsfeed.domain.comment.dto.CommentResponse;
import com.newsfeed.domain.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/comments")
    public ResponseEntity<Map<String, Long>> createComment(@RequestBody @Valid CommentRequest request) {
        // JWT 기반 사용자라면 여기서 사용자 정보 추출 예정
        Long mockWriterId = 1L; // 임시 처리
        commentService.createComment(request, mockWriterId);

        // 예: 저장 후 ID를 응답으로 반환하고 싶을 경우 처리 (이 코드는 예시이므로 실제 저장 ID를 반환하려면 서비스 수정 필요)
        Map<String, Long> response = new HashMap<>();
        response.put("commentId", 10L); // 저장된 commentId 반환하도록 변경 가능
        return ResponseEntity.ok(response);
    }

    // 댓글 수정
    @PutMapping("/comments/{id}")
    public ResponseEntity<Map<String, Long>> updateComment(@PathVariable Long id,
                                                           @RequestBody @Valid CommentRequest request) {
        Long mockWriterId = 1L; // 임시 처리
        commentService.updateComment(id, request, mockWriterId);
        Map<String, Long> response = new HashMap<>();
        response.put("commentId", id);
        return ResponseEntity.ok(response);
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        Long mockWriterId = 1L; // 임시 처리
        commentService.deleteComment(id, mockWriterId);
        return ResponseEntity.ok().build();
    }

    // 게시글의 댓글 목록 조회
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByPost(@PathVariable Long postId) {
        List<CommentResponse> response = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(response);
    }
}
