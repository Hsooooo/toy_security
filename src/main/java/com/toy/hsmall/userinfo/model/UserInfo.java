package com.toy.hsmall.userinfo.model;

import com.toy.hsmall.common.enums.Sex;
import com.toy.hsmall.security.Jwt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class UserInfo {
    private final Long userNo;
    private final String email;
    private String userName;
    private String password;
    private String ctn;
    private String status;
    private Sex sex;
    private String birthday;

    public String newApiToken(Jwt jwt, String[] roles) {
        Jwt.Claims claims = Jwt.Claims.of(userNo, userName, email, roles);
        return jwt.newToken(claims);
    }
}
