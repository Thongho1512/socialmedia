package com.oss.socialmedia.controller.request;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class UserPasswordRequest implements Serializable {
    private Long id;
    private String password;
    private String comfirmPassword;

}
