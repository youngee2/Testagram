package com.newsfeed.testagram.domain.login.controller;

import com.newsfeed.testagram.domain.login.dto.LoginRequest;
import com.newsfeed.testagram.domain.login.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        return ResponseEntity.
                status(HttpStatus.OK)
                .body(loginService.login(request.getEmail(),request.getPassword()));
    }

    @GetMapping("/member/me")
    public ResponseEntity<?> getMyInfo(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok("내 아이디: " + username);
    }

}
