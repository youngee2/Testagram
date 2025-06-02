package com.newsfeed.testagram.common.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Slf4j(topic = "JwtUtil")
@Component
public class JwtUtil {

    /**
     * JWT 토큰의 접두사입니다.
     * 예시 값: "Bearer "
     */
    public static final String BEARER_PREFIX = "Bearer ";

    /**
     * JWT 토큰의 유효 시간 (단위: 밀리초)
     * 현재 설정: 5분
     */
    private final long TOKEN_TIME = 60 * 60 * 1000L; // 5분

    @Value("${jwt.secret.key}")
    private String secretKey;

    private SecretKey key;

    /**
     * JWT 서명에 사용할 SecretKey 초기화 메서드입니다.
     * 애플리케이션 시작 시 실행됩니다.
     */
    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    /**
     * 주어진 이메일과 회원 ID 정보를 기반으로 JWT 토큰을 생성합니다.
     *
     * @param email 사용자 이메일
     * @param id 사용자 회원 ID
     * @return Bearer 접두사가 포함된 JWT 토큰 문자열
     */
    public String createToken(String email, long id) {
        Date date = new Date();
        return BEARER_PREFIX +
                Jwts.builder()
                        .subject(email)
                        .claim("memberId", id)
                        .issuedAt(date)
                        .expiration(new Date(date.getTime() + TOKEN_TIME))
                        .signWith(key)
                        .compact();
    }

    /**
     * 전달받은 JWT 토큰을 파싱하여 Claims 정보를 반환합니다.
     *
     * @param token JWT 토큰 문자열 (Bearer 접두사 포함 가능)
     * @return JWT Claims 객체 (subject, memberId 등 포함)
     */
    public Claims parseToken(String token) {
        token = token.replace(BEARER_PREFIX, "");
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * JWT 토큰에서 이메일(subject)을 추출합니다.
     *
     * @param token JWT 토큰 문자열
     * @return 이메일 (subject 값)
     */
    public String getEmailFromToken(String token) {
        return parseToken(token).getSubject();
    }

    /**
     * JWT 토큰에서 회원 ID(memberId)를 추출합니다.
     *
     * @param token JWT 토큰 문자열
     * @return 회원 ID (Long 타입)
     */
    public Long getMemberIdFormToken(String token) {
        return parseToken(token).get("memberId", Long.class);
    }

    /**
     * 전달받은 JWT 토큰의 유효성을 검증합니다.
     * - 서명 검증 실패
     * - 만료된 토큰
     * - 지원되지 않는 토큰
     * - 잘못된 형식의 토큰 등 검증 실패 시 false 반환
     *
     * 예시 메시지:
     * "Invalid JWT signature, 유효하지 않는 JWT 서명 입니다."
     * "Expired JWT token, 만료된 JWT token 입니다."
     * "Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다."
     * "JWT claims is empty, 잘못된 JWT 토큰 입니다."
     *
     * @param token JWT 토큰 문자열
     * @return 유효한 경우 true, 그렇지 않은 경우 false
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다.", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.", e);
        }
        return false;
    }

}
