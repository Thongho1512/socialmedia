package com.oss.socialmedia.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.oss.socialmedia.common.UserStatus;
import com.oss.socialmedia.controller.request.UserCreationRequest;
import com.oss.socialmedia.controller.request.UserPasswordRequest;
import com.oss.socialmedia.controller.request.UserUpdateRequest;
import com.oss.socialmedia.controller.response.UserDTO;
import com.oss.socialmedia.controller.response.UserPageDTO;
import com.oss.socialmedia.exception.ResourceNotFoundException;
import com.oss.socialmedia.model.UserEntity;
import com.oss.socialmedia.repository.UserRepository;
import com.oss.socialmedia.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j(topic = "USER-SERVICE")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    // @NonNull
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserPageDTO findAll(String keyword, String sort, int page, int size) {

        // Sorting
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        if (StringUtils.hasLength(sort)) {
            Pattern pattern = Pattern.compile("(\\w+)(:)(.*)"); // tencot:asc/desc
            Matcher matcher = pattern.matcher(sort);
            if (matcher.find()) {
                String columnName = matcher.group(1);
                if (matcher.group(3).equalsIgnoreCase("asc")) {
                    order = new Sort.Order(Sort.Direction.ASC, columnName);
                } else {
                    order = new Sort.Order(Sort.Direction.DESC, columnName);
                }
            }
        }

        //
        int page1 = 0;
        if (page > 0) {
            page1 = page - 1;
        }
        // Paging
        Pageable pageable = PageRequest.of(page1, size, Sort.by(order));
        Page<UserEntity> entityPage;
        if (StringUtils.hasLength(keyword)) {
            keyword = ".*" + keyword.toLowerCase() + ".*";
            entityPage = userRepository.searchByKeyword(keyword, pageable);
        } else {
            entityPage = userRepository.findAll(pageable);
        }
        return getUserPageDTO(page, size, entityPage);
    }

    

    // used for controller
    @Override
    public UserDTO findById(String id) {
        UserEntity user = getUser(id);
        UserDTO dto = new UserDTO.Builder()
                .setDob(user.getDob())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setGender(user.getGender())
                .setId(id)
                .setLastName(user.getLastName())
                .setPhoneNumber(user.getPhoneNumber())
                .setUserName(user.getUsername())
                .build();
        return dto;
    }


    @Override
    public UserDTO findByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }

    @Override
    public UserDTO findByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public String add(UserCreationRequest req) {
        log.info("Save user: {}", req);
        UserEntity user = new UserEntity();
        user.setLastName(req.getLastName());
        user.setUsername(req.getUsername());
        user.setDob(req.getDob());
        user.setEmail(req.getEmail());
        user.setFirstName(req.getFirstName());
        user.setGender(req.getGender());
        user.setPhoneNumber(req.getPhoneNumber());
        user.setStatus(req.getStatus().ACTIVE);
        user.setType(req.getType());
        user.setCreatedAt(Instant.now());
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public void update(UserUpdateRequest req) {
        log.info("Update user: {}", req);
        UserEntity user = getUser(req.getId());
        user.setLastName(req.getLastName());
        user.setDob(req.getDob());
        user.setEmail(req.getEmail());
        user.setFirstName(req.getFirstName());
        user.setGender(req.getGender());
        user.setPhoneNumber(req.getPhoneNumber());
        user.setUpdatedAt(Instant.now());
        userRepository.save(user);
    }

    @Override
    public void changePassword(UserPasswordRequest req) {
        log.info("Change password for user: {}", req);
        UserEntity user = getUser(req.getId());
        if (req.getComfirmPassword().equals(req.getComfirmPassword()))
            user.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void delete(String id) {
        log.info("Delete user has id: {}", id);
        UserEntity user = getUser(id);
        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
        log.info("Delete user successfully: {}", user);
    }

    // used for service
    private UserEntity getUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    // configure the object to be returned to the controller
    private UserPageDTO getUserPageDTO(int page, int size, Page<UserEntity> userEntities) {
        List<UserDTO> usersList = userEntities.stream().map(
                entity -> new UserDTO.Builder()
                        .setDob(entity.getDob())
                        .setEmail(entity.getEmail())
                        .setFirstName(entity.getFirstName())
                        .setGender(entity.getGender())
                        .setId(entity.getId())
                        .setLastName(entity.getLastName())
                        .setPhoneNumber(entity.getPhoneNumber())
                        .setUserName(entity.getUsername())
                        .build())
                .toList();
        UserPageDTO res = new UserPageDTO();
        res.setPageNumber(page);
        res.setPageSize(size);
        res.setTotalElements(userEntities.getTotalElements());
        res.setTotalPages(userEntities.getTotalPages());
        res.setUsers(usersList);
        return res;
    }

}
