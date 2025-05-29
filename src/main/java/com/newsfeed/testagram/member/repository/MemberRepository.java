package com.newsfeed.testagram.member.repository;

import com.newsfeed.testagram.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
