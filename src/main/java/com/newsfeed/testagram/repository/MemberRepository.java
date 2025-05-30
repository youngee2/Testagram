package com.newsfeed.testagram.repository;

import com.newsfeed.testagram.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberById(Long id);

    default Member findMemberByIdOrElseThrow(Long id) {
        return findMemberById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = " + id));
    }
}
