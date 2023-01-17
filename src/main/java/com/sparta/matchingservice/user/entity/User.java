package com.sparta.matchingservice.user.entity;

import com.sparta.matchingservice.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Embedded
    private Profile profile;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SellerEnrollment sellerEnrollment;

    @Builder
    public User(Long id , String userName, String password, Profile profile, UserRole userRole, SellerEnrollment sellerEnrollment) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.profile = profile;
        this.userRole = userRole;
        this.sellerEnrollment = sellerEnrollment;
    }


    public void modufyProfile(String nickName, String image) {
        this.profile = Profile.builder()
                .nickName(nickName)
                .profileImage(image)
                .build();

    }
}
