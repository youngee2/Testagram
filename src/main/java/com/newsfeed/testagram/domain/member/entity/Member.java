package com.newsfeed.testagram.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE member SET active = false WHERE id = ?")
@Where(clause = "active = true")
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

    @Column(nullable = false)
    private Boolean active=true;

    public Member(String email, String encode, String nickname) {
        this.email = email;
        this.password = encode;
        this.nickname = nickname;
    }

    public void updateProfile(String nickname, String image) {
        if (nickname != null) this.nickname = nickname;
        if (image != null) this.image = image;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void deleteProfile() {
        this.active = false;
    }


}
