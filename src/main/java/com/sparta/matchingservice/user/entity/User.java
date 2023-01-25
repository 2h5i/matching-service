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

    @Builder
    public User(String userName, String password, Profile profile, UserRole userRole, boolean isSeller) {
        this.userName = userName;
        this.password = password;
        this.profile = profile;
        this.userRole = userRole;
    }

    public void changeAuthoritySellerToCustomer() {
        this.profile = Profile.createWithoutIntroduce()
                .nickName(this.profile.getNickName())
                .profileImage(this.getProfile().getProfileImage())
                .build();
        this.userRole = UserRole.USER;
    }

    public void changeAuthorityCustomerToSeller(String introduce) {
        this.profile = Profile.createWithIntroduce()
                .nickName(this.profile.getNickName())
                .profileImage(this.profile.getProfileImage())
                .introduce(introduce)
                .build();
        this.userRole = UserRole.SELLER;

    }

    public void modufyProfile(String nickName, String image) {
        this.profile = Profile.createWithoutIntroduce()
                .nickName(nickName)
                .profileImage(image)
                .build();
    }

}
