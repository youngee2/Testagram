package com.newsfeed.testagram.domain.member.repository;

import com.newsfeed.testagram.common.exception.member.EmailNotFoundException;
import com.newsfeed.testagram.common.exception.member.MemberNotFoundException;
import com.newsfeed.testagram.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    default Member findByEmailOrThrow(String email){
        return this.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);
    }
    Optional<Member> findById(long id);
    default Member findByIdOrThrow(long id){
        return this.findById(id)
                .orElseThrow(MemberNotFoundException::new);
    }

    Optional<Member> findByNickname(String nickname);
}