package com.sparta.matchingservice.user.dto;

import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponseSellerAdmin {

    private String userName;
    private String nickName;
    private String profileImage;
    private String introduce;
    private UserRole userRole;

    private ResponseSellerAdmin(User user) {
        this.userName = user.getUserName();
        this.nickName = user.getProfile().getNickName();
        this.profileImage = user.getProfile().getProfileImage();
        this.introduce = user.getProfile().getIntroduce();
        this.userRole = user.getUserRole();
    }

    public static ResponseSellerAdmin of(User user) {
        return new ResponseSellerAdmin(user);
    }

    public static List<ResponseSellerAdmin> of(List<User> users) {
        return users.stream().map(ResponseSellerAdmin::of).collect(Collectors.toList());
    }

}
