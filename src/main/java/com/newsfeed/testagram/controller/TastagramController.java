package com.newsfeed.testagram.controller;

import com.newsfeed.testagram.dto.CreatePostRequestDto;
import com.newsfeed.testagram.dto.CreatePostResponseDto;
import com.newsfeed.testagram.service.CreatePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class TastagramController {

    private final CreatePostService createPostService;

    //게시물 생성
    @PostMapping
    public ResponseEntity<CreatePostResponseDto> save(@RequestBody CreatePostRequestDto requestDto){

        CreatePostResponseDto createPostResponseDto =
                createPostService.save(requestDto.getContent());
        return ResponseEntity.ok(createPostResponseDto);
    }



}
