package com.newsfeed.testagram.domain.post.service;

import com.newsfeed.testagram.common.exception.member.MemberNotFoundException;
import com.newsfeed.testagram.common.exception.post.PostNotFoundException;
import com.newsfeed.testagram.domain.member.entity.Member;
import com.newsfeed.testagram.domain.post.dto.request.CreatePostRequestDto;
import com.newsfeed.testagram.domain.post.dto.request.PostUpdateRequestDto;
import com.newsfeed.testagram.domain.post.dto.response.*;
import com.newsfeed.testagram.domain.post.entity.Post;
import com.newsfeed.testagram.domain.post.repository.PostRepository;
import com.newsfeed.testagram.domain.post.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    //    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CreatePostResponseDto save(CreatePostRequestDto requestDto) {
        Member user = userRepository.findById(requestDto.getWriterId())
                .orElseThrow(MemberNotFoundException::new);// 이 부분 수정
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

    //전체 조회기능

    public PostListResponseDto getPosts(Pageable pageable) {
        Page<Post> postPage = postRepository.findAll(pageable);

        Page<PostContentDto> postList = postPage.map(post -> {
            Member writer = post.getWriter();
            return new PostContentDto(
                    post.getId(),
                    writer != null ? writer.getId() : -1L,
                    writer != null ? writer.getNickname() : "탈퇴한 사용자",
                    post.getContent()
            );
        });

        return PostListResponseDto.successOf(postList);
    }

    //단건 조회기능
    @Transactional(readOnly = true)
    public PostDetailResponseDto findById(Long id) {
        //데이터 조회
        Optional<Post> findPost = postRepository.findById(id);
        //responseDto 만들기
        if (findPost.isPresent()) {
            Post post = findPost.get();
            Member writer = post.getWriter();

            String nickName = (writer != null) ? writer.getNickname() : "탈퇴한 사용자";

            return new PostDetailResponseDto(
                    200,
                    "게시물을 조회하였습니다.",
                    nickName,
                    post.getContent(),
                    post.getCreatedAt(),
                    post.getUpdatedAt()
            );
        }
        // 게시물이 없을 경우 기본 응답 또는 예외 처리
        return new PostDetailResponseDto(
                404,
                "게시물을 찾을 수 없습니다.",
                "",
                "",
                null,
                null
        );


    }

    // 수정 기능
    public PostUpdateResponseDto updatePostService(Long postId, PostUpdateRequestDto requestDto) {
        // 데이터
        String content = requestDto.getContent();
        // 조회
        Post post = postRepository.findById(postId)
            .orElseThrow(PostNotFoundException::new);

        // 수정
        post.updatePost(content);
        return new PostUpdateResponseDto(200, "수정을 완료하였습니다.");
    }

    // 삭제기능

    public PostDeleteResponseDto deletePostService(Long postId) {
        // 조회
        Optional<Post> PostOptional = postRepository.findById(postId);
        if (PostOptional.isPresent()) {
            Post foundPost = PostOptional.get();
            postRepository.delete(foundPost);
            // dto 만들기
            // dto 반환
            return new PostDeleteResponseDto(200, "deleted");
        } else {
            // 실패 용 응답 만들어서 반환
            return null;
        }
    }
}

