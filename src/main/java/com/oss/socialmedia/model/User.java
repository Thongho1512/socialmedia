package com.oss.socialmedia.model;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String username;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String profileImage; // URL to profile image
    private String bio; // Short description
    private String role;
    private List<String> friends; // Friend IDs
    private List<String> friendRequests; // Incoming friend request IDs
    private List<String> blockedUsers; // Blocked user IDs
    private Instant createdAt;
    private Instant updatedAt;
}
