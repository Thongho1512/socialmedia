package com.oss.socialmedia.controller.request;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

import com.oss.socialmedia.common.Gender;
import com.oss.socialmedia.common.UserStatus;
import com.oss.socialmedia.common.UserType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@ToString
public class UserCreationRequest implements Serializable {

    @NotBlank(message = "Lastname must not be blank")
    private String lastName;
    @NotBlank(message = "Firstname must not be blank")
    private String firstName;
    @NotBlank(message = "Username must not be blank")
    @Size(min = 1, max = 255, message = "Username must be between 1 and 255 characters")
    private String username;
    private Date dob;
    @Email(message = "Email invalid")
    private String email;
    private String phoneNumber;
    private Gender gender;
    private UserStatus status;
    private UserType type;
}
