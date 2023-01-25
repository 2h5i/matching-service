package com.sparta.matchingservice.user.dto;

import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.UserRole;
import lombok.Data;

@Data
public class UsersignupRequestDto {

    private Long id;
    private String userName;
    private String password;
    private Profile profile;
    private UserRole userRole;

}
