// package com.example.demo.controller;
// import com.example.demo.dto.*;
// import com.example.demo.service.UserService;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// @Tag(name = "Authentication")
// public class AuthController {

//     private final UserService userService;

//     public AuthController(UserService userService) {
//         this.userService = userService;
//     }

//     @PostMapping("/register")
//     public void register(@RequestBody RegisterRequest request) {
//         userService.registerUser(request);
//     }

//     @PostMapping("/login")
//     public AuthResponse login(@RequestBody LoginRequest request) {
//         return userService.loginUser(request);
//     }
// }
