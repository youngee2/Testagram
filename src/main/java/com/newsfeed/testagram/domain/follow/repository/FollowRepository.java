package com.newsfeed.testagram.domain.follow.repository;

import com.newsfeed.testagram.common.exception.follow.NoFollowersException;
import com.newsfeed.testagram.common.exception.follow.NoFollowingMembersException;
import com.newsfeed.testagram.common.exception.follow.FollowNotFoundException;
import com.newsfeed.testagram.domain.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId);

    List<Follow> findFollowByFollowerId(Long loginMemberId);

    default List<Follow> findFollowByFollowerIdOrElseThrow(Long loginMemberId) {
        List<Follow> followList = findFollowByFollowerId(loginMemberId);
        if (followList.isEmpty()) {
            throw new NoFollowingMembersException();
        }
        return followList;
    }


    List<Follow> findFollowByFollowingId(Long loginMemberId);

    default List<Follow> findFollowByFollowingIdOrElseThrow(Long loginMemberId) {
        List<Follow> followList = findFollowByFollowingId(loginMemberId);
        if (followList.isEmpty()) {
            throw new NoFollowersException();
        }
        return followList;
    }

    Optional<Follow> findFollowByFollowerIdAndFollowingId(Long followerId, Long followingId);

    default Follow findFollowByFollowerIdAndFollowingIdOrElseThrow(Long followerId, Long followingId) {
        return findFollowByFollowerIdAndFollowingId(followerId, followingId)
                .orElseThrow(FollowNotFoundException::new);
    }
}
