package com.newsfeed.testagram.domain.like.controller;

//import com.newsfeed.common.util.JwtUtil;
import com.newsfeed.testagram.common.util.JwtUtil;
import com.newsfeed.testagram.domain.like.service.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final JwtUtil jwtUtil;


    //헤더에서 토큰 추출하기
    private Long extractMemberId(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith(JwtUtil.BEARER_PREFIX)) {
            token = token.replace(JwtUtil.BEARER_PREFIX, "");
            return jwtUtil.getMemberIdFromToken(token);
        } else {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
    }


    // 게시글 좋아요
    @PostMapping("/posts/{postId}")
    public ResponseEntity<Void> likePost(
            @PathVariable Long postId,
            HttpServletRequest request
    ) {
        //Long memberId = 1L;
        Long memberId = extractMemberId(request);
        likeService.likePost(postId, memberId);
        return ResponseEntity.ok().build();
    }

    // 게시글 좋아요 취소
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> unlikePost(
            @PathVariable Long postId,
            HttpServletRequest request
    ) {
        //Long memberId = 1L;
        Long memberId = extractMemberId(request);
        likeService.unlikePost(postId, memberId);
        return ResponseEntity.ok().build();
    }

    // 댓글 좋아요
    @PostMapping("/comments/{commentId}")
    public ResponseEntity<Void> likeComment(
            @PathVariable Long commentId,
            HttpServletRequest request
    ) {
        //Long memberId = 1L;
        Long memberId = extractMemberId(request);
        likeService.likeComment(commentId, memberId);
        return ResponseEntity.ok().build();
    }

    // 댓글 좋아요 취소
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> unlikeComment(
            @PathVariable Long commentId,
            HttpServletRequest request
    ) {
        //Long memberId = 1L;
        Long memberId = extractMemberId(request);
        likeService.unlikeComment(commentId, memberId);
        return ResponseEntity.ok().build();
    }

}
