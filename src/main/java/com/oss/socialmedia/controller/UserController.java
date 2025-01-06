package com.oss.socialmedia.controller;

import java.util.List;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oss.socialmedia.model.User;
import com.oss.socialmedia.security.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<User> handleCreateUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.handleCreateUser(user));
    }

    @GetMapping("")
    public ResponseEntity<List<User>> handleFetchUser() {
        return ResponseEntity.ok(userService.handleFetchAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> handleDeleteUser(@Valid @PathVariable("id") String id) {
        return ResponseEntity.noContent().build();
    }

    @PutMapping("")
    public ResponseEntity<User> handleUpdateUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.handleUpdateUser(user));
    }
}
