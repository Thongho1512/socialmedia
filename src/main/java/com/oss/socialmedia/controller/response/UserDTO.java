package com.oss.socialmedia.controller.response;

import java.io.Serializable;
import java.util.Date;

import com.oss.socialmedia.common.Gender;

public class UserDTO implements Serializable {
    private String id;
    private String lastName;
    private String firstName;
    private Date dob;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private String username;

    public void setId(String id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public String getUsername() {
        return username;
    }

    public static class Builder {
        private final UserDTO user = new UserDTO();

        public Builder setId(String id) {
            user.setId(id);
            return this;
        }

        public Builder setFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public Builder setLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        public Builder setDob(Date dob) {
            user.setDob(dob);
            return this;
        }

        public Builder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            user.setPhoneNumber(phoneNumber);
            return this;
        }

        public Builder setGender(Gender gender) {
            user.setGender(gender);
            return this;
        }

        public Builder setUserName(String userName) {
            user.setUsername(userName);
            return this;
        }

        public UserDTO build() {
            return user;
        }
    }
}