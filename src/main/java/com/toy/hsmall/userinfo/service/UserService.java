package com.toy.hsmall.userinfo.service;

import com.toy.hsmall.userinfo.model.UserInfo;
import com.toy.hsmall.userinfo.vo.Join;
import com.toy.hsmall.userinfo.vo.Login;

public interface UserService {
    void join(Join.Request request);

    UserInfo login(Login.Request request) throws Exception;
}
