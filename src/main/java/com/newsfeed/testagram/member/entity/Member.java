package com.newsfeed.testagram.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Table(name="member")
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(length = 255)
    private String image;

    @Column(length = 255)
    private String password;

    @Column(length = 255)
    private String nickname;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="update_at")
    private LocalDateTime updateAt;

    @Column
    private Boolean active;

    public Member(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public void updateProfile(String nickname, String image) {
        if (nickname != null) this.nickname = nickname;
        if (image != null) this.image = image;
    }

}


