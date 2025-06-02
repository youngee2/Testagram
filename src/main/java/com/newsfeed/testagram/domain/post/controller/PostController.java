package com.newsfeed.testagram.domain.post.controller;

import com.newsfeed.testagram.domain.post.dto.request.CreatePostRequestDto;
import com.newsfeed.testagram.domain.post.dto.request.PostUpdateRequestDto;
import com.newsfeed.testagram.domain.post.dto.response.*;
import com.newsfeed.testagram.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    //게시물 삭제 API
    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDeleteResponseDto>deletePostAPI(
            @PathVariable("postId")Long postId

    ){
        PostDeleteResponseDto responseDto = postService.deletePostService(postId);
        ResponseEntity<PostDeleteResponseDto> response = new ResponseEntity<>(responseDto,HttpStatus.OK);
        return response;
    }

    @GetMapping("/search")
    public ResponseEntity<Page<SearchPostResponseDto>> searchPost(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        Page<SearchPostResponseDto> searchPostResponseDtoPage = postService.searchPost(from, to, pageable);

        return ResponseEntity.ok(searchPostResponseDtoPage);
    }

}
