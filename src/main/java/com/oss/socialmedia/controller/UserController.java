package com.oss.socialmedia.controller;

import com.oss.socialmedia.controller.request.UserCreationRequest;
import com.oss.socialmedia.controller.request.UserPasswordRequest;
import com.oss.socialmedia.controller.request.UserUpdateRequest;
import com.oss.socialmedia.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User controller")
@Slf4j(topic = "USER-CONTROLLER")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @Operation(summary = "users", description = "Fetch all users")
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getList(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Status", HttpStatus.OK.value());
        map.put("Message", "Get all users successfully");
        map.put("Data", userService.findAll(keyword, sort, page, size));
        return ResponseEntity.ok(map);
    }

    @Operation(summary = "Users", description = "API get user details by id")
    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable String userId) {

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Status", HttpStatus.OK.value());
        map.put("Message", "Get user successfully");
        map.put("Data", userService.findById(userId));
        return ResponseEntity.ok(map);
    }

    @Operation(summary = "Users", description = "API create new user")
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> userCreation(@Valid @RequestBody UserCreationRequest user) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Status", HttpStatus.OK.value());
        map.put("Message", "User created successfully");
        map.put("Data", userService.add(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    @Operation(summary = "Users", description = "API update user")
    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateUser(@Valid @RequestBody UserUpdateRequest req) {
        log.info("Update user: {}", req);
        userService.update(req);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Status", HttpStatus.OK.value());
        map.put("Message", "update user successfully");
        map.put("Data", "");
        return ResponseEntity.ok(map);
    }

    @Operation(summary = "Users", description = "API change user password")
    @PatchMapping("/change-pwd")
    public ResponseEntity<Map<String, Object>> changeUserPassword(@Valid @RequestBody UserPasswordRequest req) {
        userService.changePassword(req);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Status", HttpStatus.OK.value());
        map.put("Message", "Password updated successfully!");
        map.put("Data", "success");
        return ResponseEntity.ok(map);
    }

    @Operation(summary = "Users", description = "API delete user")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable String userId) {
        userService.delete(userId);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Status", HttpStatus.OK.value());
        map.put("Message", "Delete user successfully!");
        map.put("Data", "success");
        return ResponseEntity.ok(map);
    }

}
