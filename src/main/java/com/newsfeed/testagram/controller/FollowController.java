package com.newsfeed.testagram.controller;

import com.newsfeed.testagram.dto.follow.*;
import com.newsfeed.testagram.service.FollowService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/follows")
@AllArgsConstructor
public class FollowController {

    private final FollowService followService;

    /**
     * 팔로우 API
     * @param followRequestDto
     * @Param userDetails - 로그인한 사용자 정보
     * @return ResponseEntity.ok(followResponseDto) - 팔로우 정보(팔로우 고유 식별자, 팔로우한 멤버 id, 팔로우 당한 멤버 id, 팔로우 당한 멤버 email), 상태코드 200
    **/
    @PostMapping
    public ResponseEntity<FollowResponseDto> follow(
            @RequestBody FollowRequestDTO followRequestDto
            //, @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        //Long loginMemberId = userDetails.getMemberId();
        Long loginMemberId = (long) 1;
        Long targetMemberId = followRequestDto.getTargetMemberId();

        FollowResponseDto followResponseDto = followService.followMember(loginMemberId, targetMemberId);
        return ResponseEntity.ok(followResponseDto);
    }

    /**
     * 로그인한 멤버가 팔로우한 목록 조회 API
     * @Param userDetails - 로그인한 사용자 정보
     * @return ResponseEntity.ok(FollowingMemberResponseDtoList) - 팔로우한 멤버의 정보(id, nickname, email) 리스트, 상태코드 200
     **/
    @GetMapping("/followings")
    public ResponseEntity<List<FollowingMemberResponseDto>> findFollowingMember(
            // @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        //Long loginMemberId = userDetails.getMemberId();
        Long loginMemberId = (long) 1;
        List<FollowingMemberResponseDto> FollowingMemberResponseDtoList = followService.findFollowingMembers(loginMemberId);

        return ResponseEntity.ok(FollowingMemberResponseDtoList);
    }

    @GetMapping("/followers")
    public ResponseEntity<List<FollowerMemberResponseDto>> findFollowerMember(
            // @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        //Long loginMemberId = userDetails.getMemberId();
        Long loginMemberId = (long) 2;
        List<FollowerMemberResponseDto> followerMemberResponseDtoList = followService.findFollowerMembers(loginMemberId);

        return ResponseEntity.ok(followerMemberResponseDtoList);
    }

    @DeleteMapping("/{targetMemberId}")
    public ResponseEntity<Void> deleteFollowMember(
            // @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long targetMemberId
    ) {
        //Long loginMemberId = userDetails.getMemberId();
        Long loginMemberId = (long) 1;

        followService.delete(loginMemberId, targetMemberId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/followings/posts")
    public ResponseEntity<Page<FollowingMemberPostResponseDto>> findFollowingMemberPost(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            // @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        //Long loginMemberId = userDetails.getMemberId();
        Long loginMemberId = (long) 1;
        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        Page<FollowingMemberPostResponseDto> followingMemberPostResponseDtoPage = followService.findFollowingMemberPosts(loginMemberId, pageable);

        return ResponseEntity.ok(followingMemberPostResponseDtoPage);
    }
}
