package com.newsfeed.testagram.service;

import com.newsfeed.testagram.dto.follow.FollowResponseDto;
import com.newsfeed.testagram.entity.Follow;
import com.newsfeed.testagram.entity.Member;
import com.newsfeed.testagram.repository.FollowRepository;
import com.newsfeed.testagram.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public FollowResponseDto followMember(Long loginMemberId, Long targetMemberId) {

        Member follower = memberRepository.findMemberByIdOrElseThrow(loginMemberId);
        Member following = memberRepository.findMemberByIdOrElseThrow(targetMemberId);

        if (loginMemberId.equals(targetMemberId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "자기 자신을 팔로우 할 수 없습니다.");
        }

        if (followRepository.existsByFollowerIdAndFollowingId(loginMemberId, targetMemberId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 팔로우한 멤버 입니다.");
        }

        Follow follow = new Follow(loginMemberId, targetMemberId, LocalDateTime.now());
        followRepository.save(follow);

        return new FollowResponseDto(loginMemberId, targetMemberId, following.getEmail());
    }
}
