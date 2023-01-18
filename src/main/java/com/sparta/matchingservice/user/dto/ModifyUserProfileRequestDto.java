package com.sparta.matchingservice.user.dto;


import lombok.Getter;

@Getter
public class ModifyUserProfileRequestDto {

    private String nickName;
    private String profileImage;

    public ModifyUserProfileRequestDto(String nickName, String profileImage) {
        this.nickName = nickName;
        this.profileImage = profileImage;
    }
}
