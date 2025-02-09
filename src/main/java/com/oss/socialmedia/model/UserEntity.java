package com.oss.socialmedia.model;

import java.time.Instant;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.oss.socialmedia.common.Gender;
import com.oss.socialmedia.common.UserStatus;
import com.oss.socialmedia.common.UserType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;
    @Field("last_name")
    private String lastName;
    @Field("first_name")
    private String firstName;
    @NotNull(message = "Username cannot be null")
    @Size(min = 1, max = 255, message = "Username must be between 1 and 255 characters")
    private String username;
    @NotNull(message = "Password cannot be null")
    @Size(min = 1, max = 255, message = "Password must be between 1 and 255 characters")
    private String password;
    @Field("date_of_birth")
    private Date dob;
    @NotNull(message = "Email cannot be null")
    @Size(min = 1, max = 255, message = "Email must be between 1 and 255 characters")
    private String email;
    @Field("phone_number")
    private String phoneNumber;
    private Gender gender;
    private UserType type;
    private UserStatus status;
    @Field("created_at")
    private Instant createdAt;
    @Field("updated_at")
    private Instant updatedAt;

}
