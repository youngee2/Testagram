package com.newsfeed.testagram.domain.member.controller;

import com.newsfeed.testagram.domain.member.dto.MemberSignUpRequest;
import com.newsfeed.testagram.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody MemberSignUpRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.signup(request.getEmail(),request.getPassword(),request.getMemberName()));
    }
}
