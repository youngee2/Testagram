package com.newsfeed.testagram.domain.post.repository;

import com.newsfeed.testagram.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findFollowByWriterIdIn(Iterable<Long> writerIds, Pageable pageable);

    Page<Post> findAllByCreatedAtBetween(LocalDateTime from, LocalDateTime to, Pageable pageable);

}
