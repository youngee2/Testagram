package com.newsfeed.testagram.repository;

import com.newsfeed.testagram.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>  {
}
