package com.oss.socialmedia.controller.request;

import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public class UserCreationRequest implements Serializable {
    private String lastName;
    private String firstName;
    private Date dob;
    private String email;
    private String phoneNumber;
    private String gender;
    private String username;
}
