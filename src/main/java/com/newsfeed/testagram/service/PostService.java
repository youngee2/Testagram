package com.newsfeed.testagram.service;

import com.newsfeed.testagram.dto.post.SearchPostResponseDto;
import com.newsfeed.testagram.entity.Member;
import com.newsfeed.testagram.entity.Post;
import com.newsfeed.testagram.repository.MemberRepository;
import com.newsfeed.testagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Page<SearchPostResponseDto> searchPost(String from, String to, Pageable pageable) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime startDate = LocalDate.parse(from, formatter).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(to, formatter).atTime(LocalTime.MAX);

        Page<Post> postPage = postRepository.findAllByCreatedAtBetween(startDate, endDate, pageable);

        List<SearchPostResponseDto> searchPostResponseDtoList = new ArrayList<>();
        for (Post post : postPage.getContent()) {
            Member writer = memberRepository.findMemberByIdOrElseThrow(post.getWriterId());
            searchPostResponseDtoList.add(SearchPostResponseDto.toSearchPostResponseDto(post, writer));
        }

        return new PageImpl<>(searchPostResponseDtoList, pageable, postPage.getTotalElements());
    }
}
