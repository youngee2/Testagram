package com.newsfeed.testagram.domain.login.service;

import com.newsfeed.testagram.common.exception.login.IncorrectPasswordException;
import com.newsfeed.testagram.common.security.PasswordEncoder;
import com.newsfeed.testagram.common.util.JwtUtil;
import com.newsfeed.testagram.domain.login.dto.LoginResponse;
import com.newsfeed.testagram.domain.member.entity.Member;
import com.newsfeed.testagram.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * 사용자의 이메일과 비밀번호를 검증하여 로그인 처리 후 JWT 토큰을 발급합니다.
     * 비밀번호가 일치하지 않으면 IncorrectPasswordException 예외가 발생합니다.
     *
     * 예시 메시지 (비밀번호 불일치 시): "비밀번호가 일치하지 않습니다."
     *
     * @param email 사용자 이메일
     * @param password 사용자 비밀번호
     * @return 발급된 JWT 토큰이 포함된 LoginResponse 객체
     */
    public LoginResponse login(String email, String password) {
        Member member = memberRepository.findByEmailOrThrow(email);
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IncorrectPasswordException();
        }

        String token = jwtUtil.createToken(email, member.getId());
        return new LoginResponse(token);
    }

    /**
     * 스프링 시큐리티에서 사용자 인증 시 호출되는 메서드입니다.
     * 이메일 또는 닉네임으로 사용자를 조회하여 UserDetails 정보를 반환합니다.
     * 사용자가 존재하지 않으면 UsernameNotFoundException 예외가 발생합니다.
     *
     * 예시 메시지 (사용자 미존재 시): "해당 사용자가 존재하지 않습니다."
     *
     * @param username 사용자 이메일 또는 닉네임
     * @return UserDetails 객체 (email, password, 권한 정보)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .or(() -> memberRepository.findByNickname(username))
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 존재하지 않습니다."));

        return new User(
                member.getEmail(),
                member.getPassword(),
                Collections.emptyList()
        );
    }

}
