package com.newsfeed.testagram.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Post")
@Builder
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    //다대일 관계의 writer_id
    @JoinColumn(name = "writer_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User writer;
//    @Column(nullable = false,name = "writer_id")
//    private Long writerId;


    //기본생성자
    protected  Post(){}

    //생성자
    public Post(String content){
        this.content = content;
    }

    public Post(Long id, String content, User writer) {
        this.id = id;
        this.content = content;
        this.writer = writer;
    }
    public void updatePost(String content){
        this.content = content;
    }









}
