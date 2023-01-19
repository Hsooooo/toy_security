package com.toy.hsmall.userinfo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Login {

    @Getter
    @AllArgsConstructor
    public static class Request {
        private String email;
        private String password;
    }
}
