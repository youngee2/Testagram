package com.newsfeed.testagram.service;

import com.newsfeed.testagram.dto.CreatePostRequestDto;
import com.newsfeed.testagram.dto.CreatePostResponseDto;
import com.newsfeed.testagram.dto.PostContentDto;
import com.newsfeed.testagram.dto.PostListResponseDto;
import com.newsfeed.testagram.entity.Post;
import com.newsfeed.testagram.entity.User;
import com.newsfeed.testagram.repository.PostRepository;
import com.newsfeed.testagram.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    //    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CreatePostResponseDto save(CreatePostRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getWriterId())
                .orElseThrow(() -> new IllegalArgumentException("헤당유저가 존재하지 않습니다."));
        // Post 객체 생성
        Post post = Post.builder()
                .writer(user)
                .content(requestDto.getContent())
                .build();
        //System.out.println("---Post저장---" );

        // DB에 저장
        postRepository.save(post);
        //System.out.println("---Post저장완료---" );

        // 응답 반환
        return new CreatePostResponseDto(200, "게시물을 작성하였습니다.");
    }

    //전체 조회기능 반환

    public PostListResponseDto getPosts(Pageable pageable) {
        Page<Post> postPage = postRepository.findAll(pageable);

        Page<PostContentDto> postList = postPage.map(post -> {
            User writer = post.getWriter();
            return new PostContentDto(
                    post.getId(),
                    writer != null ? writer.getId() : -1L,
                    writer != null ? writer.getNickName() : "탈퇴한 사용자",
//                    post.getWriter().getId(),
//                    post.getWriter().getNickName(),
                    post.getContent()
            );
        });

        return PostListResponseDto.successOf(postList);


    }
}
