package com.newsfeed.testagram.domain.post.service;

import com.newsfeed.testagram.common.exception.member.MemberNotFoundException;
import com.newsfeed.testagram.common.exception.member.MemberNotMatchedException;
import com.newsfeed.testagram.common.exception.post.PostNotFoundException;
import com.newsfeed.testagram.common.util.JwtUtil;
import com.newsfeed.testagram.domain.like.repository.PostLikeRepository;
import com.newsfeed.testagram.domain.member.entity.Member;
import com.newsfeed.testagram.domain.member.repository.MemberRepository;
import com.newsfeed.testagram.domain.post.dto.request.CreatePostRequestDto;
import com.newsfeed.testagram.domain.post.dto.request.PostUpdateRequestDto;
import com.newsfeed.testagram.domain.post.dto.response.*;
import com.newsfeed.testagram.domain.post.entity.Post;
import com.newsfeed.testagram.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    //    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PostLikeRepository postLikeRepository;

    public CreatePostResponseDto save(CreatePostRequestDto requestDto, Long memberId) {
        Member user = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);// 이 부분 수정
        // Post 객체 생성
        Post post = Post.builder()
                .writer(user)
                .content(requestDto.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
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
                    postLikeRepository.countByPost(post),
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
                0,
                null,
                null
        );


    }

    // 수정 기능
    @Transactional
    public PostUpdateResponseDto updatePostService(String token, Long postId, PostUpdateRequestDto requestDto) {

        if(!jwtUtil.validateToken((token))){
            Member member = memberRepository.findById(jwtUtil.getMemberIdFromToken(token))
                    .orElseThrow(MemberNotMatchedException::new);
        }

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
    @Transactional
    public PostDeleteResponseDto deletePostService(String token, Long postId) {

        if(!jwtUtil.validateToken((token))){
            Member member = memberRepository.findById(jwtUtil.getMemberIdFromToken(token))
                    .orElseThrow(MemberNotMatchedException::new);
        }
        // 조회
//        Optional<Post> PostOptional = postRepository.findById(postId);
        Long memberId = jwtUtil.getMemberIdFromToken(token);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(PostNotFoundException::new);
        // 게시물 조회
        Post post = postRepository.findById(postId)
                        .orElseThrow(PostNotFoundException::new);

        postRepository.delete(post);

            return new PostDeleteResponseDto(200, "deleted");

    }

    // 검색 기능
    public Page<SearchPostResponseDto> searchPost(String from, String to, Pageable pageable) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime startDate = LocalDate.parse(from, formatter).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(to, formatter).atTime(LocalTime.MAX);

        Page<Post> postPage = postRepository.findAllByCreatedAtBetween(startDate, endDate, pageable);

        List<SearchPostResponseDto> searchPostResponseDtoList = new ArrayList<>();
        for (Post post : postPage.getContent()) {
            Member writer = post.getWriter();
            searchPostResponseDtoList.add(SearchPostResponseDto.toSearchPostResponseDto(post, writer));
        }

        return new PageImpl<>(searchPostResponseDtoList, pageable, postPage.getTotalElements());
    }
}

