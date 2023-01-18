package com.sparta.matchingservice.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class UpdateProfileForm {
    private String nickName;
    private String profileImage;
    private String introduce;


    public UpdateProfileForm(String nickName, String profileImage, String introduce) {
        this.nickName = Objects.requireNonNullElse(nickName, this.getNickName());
        this.profileImage = Objects.requireNonNullElse(profileImage, this.getProfileImage());
        this.introduce = Objects.requireNonNullElse(introduce, this.getIntroduce());
    }
}
