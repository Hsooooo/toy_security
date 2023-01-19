package com.toy.hsmall.userinfo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Table(name = "user_info")
@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private Status status;

    @Getter
    enum Status {
        A, N, T
    }
}
