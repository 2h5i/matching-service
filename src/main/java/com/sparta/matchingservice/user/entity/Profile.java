package com.sparta.matchingservice.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String profileImage;

    private String introduce;

    @Builder(builderClassName = "createWithoutIntroduce", builderMethodName = "createWithoutIntroduce")
    public Profile(String nickName,String profileImage) {
        this.nickName = nickName;
        this.profileImage = profileImage;
    }

    @Builder(builderClassName = "createWithIntroduce", builderMethodName = "createWithIntroduce")
    public Profile(String nickName, String profileImage, String introduce) {
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.introduce = introduce;
    }


    public void updateSellerProfile(String nickName, String profileImage, String introduce) {
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.introduce = introduce;
    }

}
