package com.newsfeed.testagram.repository;

import com.newsfeed.testagram.entity.Post;
import com.newsfeed.testagram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
