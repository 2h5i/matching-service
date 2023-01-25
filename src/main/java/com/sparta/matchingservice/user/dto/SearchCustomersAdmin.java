package com.sparta.matchingservice.user.dto;

import lombok.Getter;

@Getter
public class SearchCustomersAdmin {

    private String userName;
    private String nickName;

    private SearchCustomersAdmin(String userName, String nickName) {
        this.userName = userName;
        this.nickName = nickName;
    }

}
