package com.toy.hsmall.security;

import com.toy.hsmall.common.model.Id;
import com.toy.hsmall.userinfo.model.Email;
import com.toy.hsmall.userinfo.model.UserInfo;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class JwtAuthentication {
    public final Id<UserInfo, Long> id;
    public final String name;

    public final String email;

    JwtAuthentication(Long id, String name, String email) {
        checkNotNull(id, "id must be provided");
        checkNotNull(name, "name must be provided");
        checkNotNull(email, "email must be provided");

        this.id = Id.of(UserInfo.class, id);
        this.name = name;
        this.email = email;
    }
}
