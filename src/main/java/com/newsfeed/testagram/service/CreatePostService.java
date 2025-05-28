package com.newsfeed.testagram.service;

import com.newsfeed.testagram.dto.CreatePostResponseDto;
import com.newsfeed.testagram.entity.Post;
import com.newsfeed.testagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;

@Service
@RequiredArgsConstructor
public class CreatePostService {

//    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CreatePostResponseDto save(String content){
        // Post 객체 생성
        Post post = new Post(content);
        // DB에 저작ㅇ
        postRepository.save(post);
      return new CreatePostResponseDto(200,"게시물을 작성하였습니다.");
    }
}
