package com.sparta.matchingservice.user.dto;

import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseCustomersAdmin {

    private Long id;
    private String userName;
    private String nickName;
    private String profileImage;
    private UserRole userRole;

    private ResponseCustomersAdmin(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.nickName = user.getProfile().getNickName();
        this.profileImage = user.getProfile().getProfileImage();
        this.userRole = user.getUserRole();
    }

    public static ResponseCustomersAdmin of(User user) {
        return new ResponseCustomersAdmin(user);
    }

    public static List<ResponseCustomersAdmin> of(List<User> users) {
        return users.stream().map(ResponseCustomersAdmin::of).collect(Collectors.toList());
    }

}
