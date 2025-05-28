package com.newsfeed.testagram.repository;

import com.newsfeed.testagram.entity.Follow;
import com.newsfeed.testagram.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId);

    List<Follow> findFollowByFollowerId(Long loginMemberId);

    default List<Follow> findFollowByFollowerIdOrElseThrow(Long loginMemberId) {
        List<Follow> result = findFollowByFollowerId(loginMemberId);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "팔로우한 멤버가 없습니다. id = " + loginMemberId);
        }
        return result;
    }
}
