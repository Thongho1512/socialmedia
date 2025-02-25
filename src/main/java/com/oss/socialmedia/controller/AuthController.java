// package com.oss.socialmedia.controller;

// import org.apache.catalina.security.SecurityUtil;
// import org.springframework.http.ResponseCookie;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.oss.socialmedia.controller.request.ReqLoginDTO;
// import com.oss.socialmedia.controller.response.ResLoginDTO;
// import com.oss.socialmedia.model.UserEntity;
// import com.oss.socialmedia.service.UserService;

// import io.swagger.v3.oas.annotations.tags.Tag;
// import jakarta.validation.Valid;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @RestController
// @Tag(name = "auth controller")
// @Slf4j(topic = "AUTH-CONTROLLER")
// @RequestMapping("/auth/login")
// @RequiredArgsConstructor
// public class AuthController {
//     private final AuthenticationManagerBuilder authenticationManagerBuilder;
//     private final SecurityUtil securityUtil;
//     private final UserService userService;
//     private final PasswordEncoder passwordEncoder;
//     @PostMapping("/auth/login")
//     public ResponseEntity<ReqLoginDTO> login(@Valid @RequestBody ReqLoginDTO loginRequest) {
//         // Nạp input gồm username/password vào Security
//         UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//             loginRequest.getUsername(), loginRequest.getPassword());

//         // xác thực người dùng => cần viết hàm loadUserByUsername
//         Authentication authentication = authenticationManagerBuilder.getObject()
//                 .authenticate(authenticationToken);

//         // set thông tin người dùng đăng nhập vào context (có thể sử dụng sau này)
//         SecurityContextHolder.getContext().setAuthentication(authentication);

//         ResLoginDTO res = new ResLoginDTO();
//         UserEntity currentUserDB = this.userService.handleGetUserByUsername(loginDto.getUsername());
//         if (currentUserDB != null) {
//             ResLoginDTO.UserLogin userLogin = new ResLoginDTO.UserLogin(
//                     currentUserDB.getId(),
//                     currentUserDB.getEmail(),
//                     currentUserDB.getUsername(),
//                     currentUserDB.getRole());
//             res.setUser(userLogin);
//         }

//         // create access token
//         String access_token = this.securityUtil.createAccessToken(authentication.getName(), res);
//         res.setAccessToken(access_token);

//         // create refresh token
//         String refresh_token = this.securityUtil.createRefreshToken(loginDto.getUsername(), res);

//         // update user
//         this.userService.updateUserToken(refresh_token, loginDto.getUsername());

//         // set cookies
//         ResponseCookie resCookies = ResponseCookie
//                 .from("refresh_token", refresh_token)
//                 .httpOnly(true)
//                 .secure(true)
//                 .path("/")
//                 .maxAge(refreshTokenExpiration)
//                 .build();

//         return ResponseEntity.ok()
//                 .header(HttpHeaders.SET_COOKIE, resCookies.toString())
//                 .body(res);
//     }
// }
