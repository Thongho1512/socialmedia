package com.oss.socialmedia.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oss.socialmedia.model.User;
import com.oss.socialmedia.security.UserService;

@RestController
@RequestMapping("/api/v1/admin")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User handleCreateUser(@RequestBody User user) {
        return userService.handleCreateUser(user);
    }

    @GetMapping("/users")
    public List<User> handleFetchUser() {
        return userService.handleFetchAllUsers();
    }
}
