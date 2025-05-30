package com.newsfeed.testagram.repository;

import com.newsfeed.testagram.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findFollowByWriterIdIn(Iterable<Long> writerIds, Pageable pageable);
}
