package com.toy.hsmall.security;

import com.toy.hsmall.userinfo.model.UserInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AuthenticationResult {
    private final String apiToken;
    private final UserInfo userInfo;

}
