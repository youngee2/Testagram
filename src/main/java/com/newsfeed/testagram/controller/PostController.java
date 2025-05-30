package com.newsfeed.testagram.controller;

import com.newsfeed.testagram.dto.post.SearchPostResponseDto;
import com.newsfeed.testagram.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

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
