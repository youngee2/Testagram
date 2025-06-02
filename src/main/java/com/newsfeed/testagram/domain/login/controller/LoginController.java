package com.newsfeed.testagram.domain.login.controller;

import com.newsfeed.testagram.common.util.JwtUtil;
import com.newsfeed.testagram.domain.login.dto.LoginRequest;
import com.newsfeed.testagram.domain.login.dto.LoginResponse;
import com.newsfeed.testagram.domain.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(loginService.login(request.getEmail(),request.getPassword()));
    }

}
