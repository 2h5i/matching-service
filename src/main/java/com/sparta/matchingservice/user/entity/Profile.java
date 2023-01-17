package com.sparta.matchingservice.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Profile {

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String profileImage;

    private String introduce;

}
