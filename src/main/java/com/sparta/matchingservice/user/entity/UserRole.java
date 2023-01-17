package com.sparta.matchingservice.user.entity;

public enum UserRole {
    USER(Authority.USER),
    SELLER(Authority.SELLER),
    ADMIN(Authority.ADMIN);

    private final String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return  this.authority;
    }

    public static class Authority{
        public static final String USER = "ROLE_USER";
        public static final String SELLER = "ROLE_SELLER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
