package com.newsfeed.testagram.domain.login.controller;

import com.newsfeed.testagram.domain.login.dto.LoginRequest;
import com.newsfeed.testagram.domain.login.dto.LoginResponse;
import com.newsfeed.testagram.domain.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    /**
     * 사용자의 로그인 요청을 처리합니다.
     * 이메일과 비밀번호를 검증하고 JWT 토큰을 발급합니다.
     *
     * 상황: 이메일 또는 비밀번호 불일치 시 예외 발생 (IncorrectPasswordException, LoginFailedException 등)
     * 예시 응답: LoginResponse { "accessToken": "Bearer ~~~" }
     *
     * @param request 로그인 요청 객체 (이메일, 비밀번호)
     * @return JWT 토큰이 포함된 LoginResponse 객체
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loginService.login(request.getEmail(), request.getPassword()));
    }
}
