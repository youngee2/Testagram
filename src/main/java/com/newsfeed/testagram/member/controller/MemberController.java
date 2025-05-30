package com.newsfeed.testagram.member.controller;



import com.newsfeed.testagram.common.security.UserDetailsImpl;
import com.newsfeed.testagram.member.dto.request.MemberSignUpRequest;
import com.newsfeed.testagram.member.dto.request.PasswordRequestDto;
import com.newsfeed.testagram.member.dto.response.MemberResponseDto;
import com.newsfeed.testagram.member.dto.response.MyProfileResponseDto;
import com.newsfeed.testagram.member.dto.request.MyProfileUpdateRequestDto;
import com.newsfeed.testagram.member.dto.response.MyProfileUpdateResponseDto;
import com.newsfeed.testagram.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 ID로 회원 정보를 조회합니다.
     *
     * @param  id 조회할 회원의 id
     * @return 회원 정보가 담긴 ResponseEntity 객체
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getProfile(@PathVariable Long id){
    MemberResponseDto responseDto = memberService.getMemberById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/me")
    public ResponseEntity<MyProfileResponseDto> getMyProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
        Long id = userDetails.getId();
        MyProfileResponseDto responseDto = memberService.getMyProfileById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/me")
    public ResponseEntity<MyProfileUpdateResponseDto> editMyProfile(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                              @RequestBody MyProfileUpdateRequestDto requestDto){
        Long id = userDetails.getId();
        MyProfileUpdateResponseDto responseDto = memberService.editMyProfileById(id,requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/me/password")
    public ResponseEntity<Void> editPassword(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                    @RequestBody PasswordRequestDto requestDto){
        Long id = userDetails.getId();
        memberService.editPasswordById(id,requestDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/register")
    public ResponseEntity<?> save(@Valid @RequestBody MemberSignUpRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.signup(request.getEmail(),request.getPassword(),request.getMemberName()));
    }
}





