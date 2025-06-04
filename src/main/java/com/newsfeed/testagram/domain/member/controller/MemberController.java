package com.newsfeed.testagram.domain.member.controller;

import com.newsfeed.testagram.domain.member.dto.request.MemberSignUpRequest;
import com.newsfeed.testagram.domain.member.dto.request.MyProfileDeleteRequestDto;
import com.newsfeed.testagram.domain.member.dto.request.MyProfileUpdateRequestDto;
import com.newsfeed.testagram.domain.member.dto.request.PasswordRequestDto;
import com.newsfeed.testagram.domain.member.dto.response.*;
import com.newsfeed.testagram.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<MemberSignUpResponse> save(@Valid @RequestBody MemberSignUpRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.signup(request.getEmail(),request.getPassword(),request.getNickname()));
    }
    /**
     * 사용자 ID로 사용자 정보를 조회합니다.
     *
     * @param  id 조회할 사용자 id
     * @return 사용자 프로필 정보 DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getProfile(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.getMemberById(id));
    }


    /**
     *로그인한 사용자 정보를 기반으로 사용자 정보를 조회합니다.
     *
     * @return 사용자 프로필 정보 DTO
     */
    @GetMapping("/me")
    public ResponseEntity<MyProfileResponseDto> getMyProfile(@RequestHeader("Authorization") String token){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.getMemberById(token));
    }

    /**
     * 로그인한 사용자 정보를 기반으로 사용자 정보를 수정합니다.
     * @param requestDto 사용자 프로필 수정 내용 DTO
     * @return 사용자 프로필 정보 DTO
     */
    @PatchMapping("/me")
    public ResponseEntity<MyProfileUpdateResponseDto> editMyProfile(@RequestHeader("Authorization") String token,
                                                                    @RequestBody MyProfileUpdateRequestDto requestDto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.editMyProfileById(token,requestDto));
    }


    /**
     * 로그인한 사용자 정보를 기반으로 사용자 비밀번호를 수정합니다.
     *
     * @param requestDto 사용자 비밀번호 수정 DTO
     * @return 비밀번호 수정 성공시 200 OK 응답
     */
    @PatchMapping("/me/password")
    public ResponseEntity<SuccessResponse> editPassword(@RequestHeader("Authorization") String token,
                                                        @RequestBody PasswordRequestDto requestDto){
        memberService.editPasswordById(token,requestDto);
        return ResponseEntity.ok(SuccessResponse.of("비밀번호가 정상적으로 수정되었습니다."));
    }


    /**
     * 로그인한 사용자 정보를 기반으로 탈퇴합니다.
     * @param dto 사용자 탈퇴 DTO
     * @return 비밀번호 탈퇴 성공시 200 OK 응답
     */
    @DeleteMapping("/status")
    public ResponseEntity<SuccessResponse> deleteProfile(@RequestHeader("Authorization") String token,
                                              @RequestBody MyProfileDeleteRequestDto dto){
        memberService.deleteProfileById(token,dto);
        return ResponseEntity.ok(SuccessResponse.of("회원탈퇴가 정상적으로 처리되었습니다."));
    }
}
