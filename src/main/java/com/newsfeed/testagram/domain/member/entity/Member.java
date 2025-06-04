package com.newsfeed.testagram.domain.member.entity;

import com.newsfeed.testagram.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Table(name="member")
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String image;
    private String password;
    private String nickname;

    @Column(nullable = false)
    private Boolean active = true;

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
