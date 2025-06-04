package com.newsfeed.testagram.domain.follow.service;

import com.newsfeed.testagram.common.exception.follow.AlreadyFollowingException;
import com.newsfeed.testagram.common.exception.follow.SelfFollowNotAllowedException;
import com.newsfeed.testagram.domain.follow.dto.follow.FollowResponseDto;
import com.newsfeed.testagram.domain.follow.dto.follow.FollowerMemberResponseDto;
import com.newsfeed.testagram.domain.follow.dto.follow.FollowingMemberPostResponseDto;
import com.newsfeed.testagram.domain.follow.dto.follow.FollowingMemberResponseDto;
import com.newsfeed.testagram.domain.follow.entity.Follow;
import com.newsfeed.testagram.domain.follow.repository.FollowRepository;
import com.newsfeed.testagram.domain.member.entity.Member;
import com.newsfeed.testagram.domain.member.repository.MemberRepository;
import com.newsfeed.testagram.domain.post.entity.Post;
import com.newsfeed.testagram.domain.post.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public FollowResponseDto followMember(Long loginMemberId, Long targetMemberId) {

        Member follower = memberRepository.findByIdOrThrow(loginMemberId);
        Member following = memberRepository.findByIdOrThrow(targetMemberId);

        if (loginMemberId.equals(targetMemberId)) {
            throw new SelfFollowNotAllowedException("자기 자신을 팔로우 할 수 없습니다.", HttpStatus.NOT_FOUND);
        }

        if (followRepository.existsByFollowerIdAndFollowingId(loginMemberId, targetMemberId)) {
            throw new AlreadyFollowingException("이미 팔로우한 멤버 입니다.", HttpStatus.BAD_REQUEST);
        }

        Follow follow = new Follow(loginMemberId, targetMemberId, LocalDateTime.now());
        followRepository.save(follow);

        return new FollowResponseDto(loginMemberId, targetMemberId, following.getNickname(), following.getEmail());
    }

    @Transactional
    public List<FollowingMemberResponseDto> findFollowingMembers(Long loginMemberId) {

        List<Follow> followList = followRepository.findFollowByFollowerIdOrElseThrow(loginMemberId);

        List<FollowingMemberResponseDto> followingMemberResponseDtoList = new ArrayList<>();

        for (Follow follow : followList) {
            Long followingMemberId = follow.getFollowingId();
            Member member = memberRepository.findByIdOrThrow(followingMemberId);

            followingMemberResponseDtoList.add(FollowingMemberResponseDto.toFollowingMemberResponseDto(member));
        }

        return followingMemberResponseDtoList;
    }

    @Transactional
    public List<FollowerMemberResponseDto> findFollowerMembers(Long loginMemberId) {

        List<Follow> followList = followRepository.findFollowByFollowingIdOrElseThrow(loginMemberId);

        List<FollowerMemberResponseDto> followerMemberResponseDtoList = new ArrayList<>();

        for (Follow follow : followList) {
            Long followerMemberId = follow.getFollowerId();
            Member member = memberRepository.findByIdOrThrow(followerMemberId);

            followerMemberResponseDtoList.add(FollowerMemberResponseDto.toFollowerMemberResponseDto(member));
        }

        return followerMemberResponseDtoList;
    }

    @Transactional
    public void delete(Long loginMemberId, Long targetMemberId) {
        Follow findFollow = followRepository.findFollowByFollowerIdAndFollowingIdOrElseThrow(loginMemberId, targetMemberId);
        followRepository.delete(findFollow);
    }

    @Transactional
    public Page<FollowingMemberPostResponseDto> findFollowingMemberPosts(Long loginMemberId, Pageable pageable) {
        List<Follow> followList = followRepository.findFollowByFollowerIdOrElseThrow(loginMemberId);

        List<Long> followingIdList = new ArrayList<>();
        for (Follow follow : followList) {
            followingIdList.add(follow.getFollowingId());
        }

        Page<Post> postPage = postRepository.findFollowByWriterIdIn(followingIdList, pageable);

        List<FollowingMemberPostResponseDto> followingMemberPostResponseDtoList = new ArrayList<>();
        for (Post post : postPage.getContent()) {
            Member writer = memberRepository.findByIdOrThrow(post.getId());
            FollowingMemberPostResponseDto followingMemberPostResponseDto = FollowingMemberPostResponseDto.tofollowingMemberPostResponseDto(post, writer);
            followingMemberPostResponseDtoList.add(followingMemberPostResponseDto);
        }

        return new PageImpl<>(followingMemberPostResponseDtoList, pageable, postPage.getTotalElements());
    }
}
