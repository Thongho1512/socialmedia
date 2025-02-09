package com.oss.socialmedia.service;

import com.oss.socialmedia.controller.request.UserCreationRequest;
import com.oss.socialmedia.controller.request.UserPasswordRequest;
import com.oss.socialmedia.controller.request.UserUpdateRequest;
import com.oss.socialmedia.controller.response.UserDTO;
import com.oss.socialmedia.controller.response.UserPageDTO;

public interface UserService {
    UserPageDTO findAll(String keyword, String sort, int page, int size);

    UserDTO findById(String id);

    UserDTO findByUsername(String username);

    UserDTO findByEmail(String email);

    String add(UserCreationRequest req);

    void update(UserUpdateRequest req);

    void changePassword(UserPasswordRequest req);

    void delete(String id);
}
