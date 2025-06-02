package com.newsfeed.testagram.domain.follow.repository;

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "팔로우한 멤버가 없습니다. id = " + loginMemberId);
        }
        return followList;
    }

    List<Follow> findFollowByFollowingId(Long loginMemberId);

    default List<Follow> findFollowByFollowingIdOrElseThrow(Long loginMemberId) {
        List<Follow> followList = findFollowByFollowingId(loginMemberId);
        if (followList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "팔로잉한 멤버가 없습니다. id = " + loginMemberId);
        }
        return followList;
    }

    Optional<Follow> findFollowByFollowerIdAndFollowingId(Long followerId, Long followingId);

    default Follow findFollowByFollowerIdAndFollowingIdOrElseThrow(Long followerId, Long followingId) {
        return findFollowByFollowerIdAndFollowingId(followerId, followingId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "팔로우 관계가 존재하지 않습니다. followerId=" + followerId + ", followingId=" + followingId));
    }
}
