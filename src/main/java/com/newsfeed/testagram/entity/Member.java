package com.newsfeed.testagram.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity


public class Member {
    @Id
    private Long id;

    private String nickName;

    protected Member(){}

    public Member(Long id, String nickName){
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

