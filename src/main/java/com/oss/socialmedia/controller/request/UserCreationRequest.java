package com.oss.socialmedia.controller.request;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

import com.oss.socialmedia.common.Gender;
import com.oss.socialmedia.common.UserStatus;
import com.oss.socialmedia.common.UserType;

@Getter
@ToString
public class UserCreationRequest implements Serializable {
    private String lastName;
    private String firstName;
    private String username;
    private Date dob;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private UserStatus status;
    private UserType type;
}
