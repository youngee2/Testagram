package com.newsfeed.testagram.domain.member.repository;

import com.newsfeed.testagram.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    default Member findByEmailOrThrow(String email){
        return this.findByEmail(email)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST," 없는 이메일"));
    }
    Optional<Member> findByNickname(String nickname);
}