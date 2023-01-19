package com.toy.hsmall.userinfo.vo;

import com.toy.hsmall.common.enums.Sex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Join {
    public static class Request {

        @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        @Size(max = 100)
        private String email;
        @Size(max = 1000)
        private String password;
        private String ctn;
        private String name;
        @NotBlank
        private Sex sex;
        @Pattern(regexp = "^((19|2[0-9])[0-9]{2})(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$")
        private String birthday;

    }
}
