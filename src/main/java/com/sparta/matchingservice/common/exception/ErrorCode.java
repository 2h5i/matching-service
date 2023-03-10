package com.sparta.matchingservice.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // NOT_FOUND
    ID_NOT_FOUND(HttpStatus.NOT_FOUND,"아이디를 찾을 수 없습니다."),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND,"유저네임을 찾을 수 없습니다."),
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND,"아이템을 찾을 수 없습니다."),
    FORBIDDEN_FROFILE(HttpStatus.FORBIDDEN,"ID 값이 달라 frofile에 접근할 수 없습니다."),
    FORBIDDEN_FOR_ORDER(HttpStatus.FORBIDDEN, "판매자만 조회할 수 있습니다."),;

    private final HttpStatus status;
    private final String message;

}
