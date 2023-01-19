package com.toy.hsmall.common.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationRequest {
    private String principal;
    private String credentials;
}
