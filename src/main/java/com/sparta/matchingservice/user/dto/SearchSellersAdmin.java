package com.sparta.matchingservice.user.dto;

import lombok.Getter;

@Getter
public class SearchSellersAdmin {

    private String userName;
    private String nickName;
    private String introduce;

    private SearchSellersAdmin(String userName, String nickName, String introduce) {
        this.userName = userName;
        this.nickName = nickName;
        this.introduce = introduce;
    }

}
