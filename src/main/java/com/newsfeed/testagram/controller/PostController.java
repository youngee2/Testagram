package com.newsfeed.testagram.controller;

import com.newsfeed.testagram.dto.*;
import com.newsfeed.testagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;

    //게시물 생성
    @PostMapping
    public ResponseEntity<CreatePostResponseDto> save(@RequestBody CreatePostRequestDto requestDto) {
        // System.out.println("---연결확인--");
        CreatePostResponseDto createPostResponseDto = postService.save(requestDto);
        return ResponseEntity.ok(createPostResponseDto);
    }

    //게시물 전체 조회
    @GetMapping
    public ResponseEntity<PostListResponseDto> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        PostListResponseDto posts = postService.getPosts(pageable);
        return ResponseEntity.ok(posts);
    }

    // 게시물 단건 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailResponseDto> getPostAPI(@PathVariable Long postid) {
        PostDetailResponseDto post = postService.findById(postid);
        return ResponseEntity.ok(post);
    }

    //게시물 수정 API
    @PatchMapping("/{postId}")
    public ResponseEntity<PostUpdateResponseDto> updatePostAPI(
            @PathVariable("postId") Long postId,
            @RequestBody PostUpdateRequestDto requestDto
    ) {
        PostUpdateResponseDto responseDto = postService.updatePostService(postId,requestDto);
        ResponseEntity<PostUpdateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;

    }

}
