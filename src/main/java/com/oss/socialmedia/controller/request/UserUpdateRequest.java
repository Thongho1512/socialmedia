package com.oss.socialmedia.controller.request;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

import com.oss.socialmedia.common.Gender;

@Getter
@ToString
public class UserUpdateRequest implements Serializable {
    private String id;
    private String lastName;
    private String firstName;
    private Date dob;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private String username;
}
