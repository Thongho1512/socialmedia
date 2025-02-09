package com.oss.socialmedia.controller.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserPasswordRequest implements Serializable {
    private String id;
    private String password;
    private String comfirmPassword;

}
