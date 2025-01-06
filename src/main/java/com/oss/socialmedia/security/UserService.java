package com.oss.socialmedia.security;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oss.socialmedia.model.User;
import com.oss.socialmedia.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> handleFetchAllUsers() {
        return userRepository.findAll();
    }

    public User handleCreateUser(User user) {
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setCreatedAt(Instant.now());
        return userRepository.save(user);
    }

    public void handleDeleteUser(String id) {
        userRepository.deleteById(id);
    }

    public User fetchUserById(String id) {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else
            return null;
    }

    public User handleUpdateUser(User user) {
        User user1 = fetchUserById(user.getId());
        user1.setBio(user.getBio());
        user1.setPassword(user.getPassword());
        user1.setProfileImage(user.getProfileImage());
        user1.setUsername(user.getUsername());
        user1.setUpdatedAt(Instant.now());
        return userRepository.save(user1);
    }
}
