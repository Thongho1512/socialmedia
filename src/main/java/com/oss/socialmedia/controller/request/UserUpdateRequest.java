package com.oss.socialmedia.controller.request;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

import com.oss.socialmedia.common.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@ToString
public class UserUpdateRequest implements Serializable {
    @NotBlank(message = "Id must not be blank")
    private String id;
    @NotBlank(message = "LastName must not be blank")
    private String lastName;
    @NotBlank(message = "FirstName must not be blank")
    private String firstName;
    private Date dob;
    @Email(message = "Email invalid")
    private String email;
    private String phoneNumber;
    private Gender gender;
    @NotBlank(message = "Username must not be blank")
    private String username;
}
