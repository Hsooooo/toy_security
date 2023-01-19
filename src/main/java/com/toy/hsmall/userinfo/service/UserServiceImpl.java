package com.toy.hsmall.userinfo.service;

import com.toy.hsmall.userinfo.model.Email;
import com.toy.hsmall.userinfo.model.UserInfo;
import com.toy.hsmall.userinfo.repository.UserInfoRepository;
import com.toy.hsmall.userinfo.vo.Join;
import com.toy.hsmall.userinfo.vo.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserInfoRepository userInfoRepository;

    @Override
    public void join(Join.Request request) {
    }

    @Override
    public UserInfo login(Login.Request request) throws Exception {
        UserInfo userInfo = userInfoRepository.findByEmail(new Email(request.getEmail())).orElseThrow(Exception::new);
        if (!userInfo.getPassword().equals(request.getPassword())) {
            throw new Exception();
        }
        return userInfo;
    }
}
