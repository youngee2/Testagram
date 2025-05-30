package com.newsfeed.testagram.member.controller;



import com.newsfeed.testagram.member.dto.response.MemberResponseDto;
import com.newsfeed.testagram.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}



