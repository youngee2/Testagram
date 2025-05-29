package com.newsfeed.testagram.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity


public class User {
    @Id
    private Long id;

    private String nickName;

    protected  User (){}

    public User(Long id, String nickName){
        this.id = id;
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }
}

