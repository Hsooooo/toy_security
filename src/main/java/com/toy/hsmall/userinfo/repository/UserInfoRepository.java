package com.toy.hsmall.userinfo.repository;

import com.toy.hsmall.common.model.Id;
import com.toy.hsmall.userinfo.model.Email;
import com.toy.hsmall.userinfo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmail(Email email);

}
