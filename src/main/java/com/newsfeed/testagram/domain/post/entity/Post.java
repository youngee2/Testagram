package com.newsfeed.testagram.domain.post.entity;

import com.newsfeed.testagram.common.BaseEntity;
import com.newsfeed.testagram.domain.member.entity.Member;
import com.newsfeed.testagram.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "Post")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    //다대일 관계의 writer_id
    @JoinColumn(name = "writer_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Member writer;
//    @Column(nullable = false,name = "writer_id")
//    private Long writerId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void updatePost(String content){
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

}
