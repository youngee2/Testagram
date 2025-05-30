package com.newsfeed.testagram.member.controller;



import com.newsfeed.testagram.common.security.UserDetailsImpl;
import com.newsfeed.testagram.member.dto.request.MyProfileDeleteRequestDto;
import com.newsfeed.testagram.member.dto.request.PasswordRequestDto;
import com.newsfeed.testagram.member.dto.response.MemberResponseDto;
import com.newsfeed.testagram.member.dto.response.MyProfileResponseDto;
import com.newsfeed.testagram.member.dto.request.MyProfileUpdateRequestDto;
import com.newsfeed.testagram.member.dto.response.MyProfileUpdateResponseDto;
import com.newsfeed.testagram.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    /**
     * 사용자 ID로 사용자 정보를 조회합니다.
     *
     * @param  id 조회할 사용자 id
     * @return 사용자 프로필 정보 DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getProfile(@PathVariable Long id){
    MemberResponseDto responseDto = memberService.getMemberById(id);
        return ResponseEntity.ok(responseDto);
    }


    /**
     *로그인한 사용자 정보를 기반으로 사용자 정보를 조회합니다.
     *
     * @param userDetails 현재 로그인한 사용자 정보
     * @return 사용자 프로필 정보 DTO
     */
    @GetMapping("/me")
    public ResponseEntity<MyProfileResponseDto> getMyProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
        Long id = userDetails.getId();
        MyProfileResponseDto responseDto = memberService.getMyProfileById(id);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 로그인한 사용자 정보를 기반으로 사용자 정보를 수정합니다.
     * @param userDetails 현재 로그인한 사용자 정보
     * @param requestDto 사용자 프로필 수정 내용 DTO
     * @return 사용자 프로필 정보 DTO
     */
    @PatchMapping("/me")
    public ResponseEntity<MyProfileUpdateResponseDto> editMyProfile(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                              @RequestBody MyProfileUpdateRequestDto requestDto){
        Long id = userDetails.getId();
        MyProfileUpdateResponseDto responseDto = memberService.editMyProfileById(id,requestDto);
        return ResponseEntity.ok(responseDto);
    }


    /**
     * 로그인한 사용자 정보를 기반으로 사용자 비밀번호를 수정합니다.
     * @param userDetails 현재 로그인한 사용자 정보
     * @param requestDto 사용자 비밀번호 수정 DTO
     * @return 비밀번호 수정 성공시 200 OK 응답
     */
    @PatchMapping("/me/password")
    public ResponseEntity<Void> editPassword(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                    @RequestBody PasswordRequestDto requestDto){
        Long id = userDetails.getId();
        memberService.editPasswordById(id,requestDto);
        return ResponseEntity.ok().build();
    }


    /**
     * 로그인한 사용자 정보를 기반으로 탈퇴합니다.
     * @param userDetails 현재 로그인한 사용자 정보
     * @param dto 사용자 탈퇴 DTO
     * @return 비밀번호 수정 성공시 200 OK 응답
     */
    @DeleteMapping("/status")
    public ResponseEntity<Void> deleteProfile(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                              @RequestBody MyProfileDeleteRequestDto dto){
        Long id = userDetails.getId();
        memberService.deleteProfileById(id,dto);
        return ResponseEntity.ok().build();
    }
}





