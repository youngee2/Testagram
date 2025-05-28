package com.newsfeed.testagram.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Post")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "content")
    private String content;

    //다대일 관계의 writer_id
//    @JoinColumn(name = "writer_id")
//    @ManyToOne(fetch = LAZY)
//    private User writerId;
    private Long writerId;

    //생성자
    public Post(String content){
        this.content = content;
    }









}
