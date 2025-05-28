package com.newsfeed.testagram.controller;

import com.newsfeed.testagram.dto.follow.FollowRequestDTO;
import com.newsfeed.testagram.dto.follow.FollowResponseDto;
import com.newsfeed.testagram.service.FollowService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/follows")
@AllArgsConstructor
public class FollowController {

    // 생성자를 통한 followService 의존성 주입
    private final FollowService followService;
    
    // 팔로우 API
    @PostMapping
    public ResponseEntity<FollowResponseDto> follow(
            @RequestBody FollowRequestDTO followRequestDto
            //, @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        //Long loginUserId = userDetails.getUserId(); // JWT에서 가져온 자신의 ID
        Long loginMemberId = (long) 1; // 테스트용
        Long targetMemberId = followRequestDto.getTargetMemberId(); // 팔로우 대상 맴버 ID

        FollowResponseDto followResponseDto = followService.followMember(loginMemberId, targetMemberId); // 자신의 ID + 팔로우 대상 ID로 팔로우 비즈니스 로직 실행
        return ResponseEntity.ok(followResponseDto); // 팔로우 완료 후 팔로우에 대한 정보 리턴
    }
}
