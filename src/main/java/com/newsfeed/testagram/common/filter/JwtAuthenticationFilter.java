package com.newsfeed.testagram.common.filter;

import com.newsfeed.testagram.common.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static com.newsfeed.testagram.common.util.JwtUtil.BEARER_PREFIX;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = httpRequest.getRequestURI();
        String authorizationHeader = httpRequest.getHeader("Authorization");


        if(requestURI.equals("/api/login") || requestURI.equals("/api/member/register")) { // 로그인하는 거라면 어차피 토큰이 없을 것임. 그렇기에 필터로 바로 보내주고 얼리리턴
            filterChain.doFilter(httpRequest,httpResponse);
            return;
        }

        // 여기왔다면 로그인 상태가 아니라는 소리임.
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json; charset=UTF-8");
            httpResponse.getWriter().write("{\n  \"error\": \"401\",   \"message\": \"토큰이 유효하지 않습니다.\"\n}");

            return;
        }

        String jwt = authorizationHeader.substring(BEARER_PREFIX.length());
        if (!jwtUtil.validateToken(jwt)) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json; charset=UTF-8");
            httpResponse.getWriter().write("{\n  \"error\": \"401\",   \"message\": \"토큰이 만료되었습니다.\"\n}");
            return;
        }

        String email = jwtUtil.getEmailFromToken(jwt);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                email,
                null,
                List.of()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(httpRequest, httpResponse);
    }
}
