package com.example.demo.dto;

import java.util.Set;

public class AuthResponse {
    private String token;
    private Long userId;
    private String email;
    private Set<String> roles;

    public AuthResponse() {}
    public AuthResponse(String token, Long userId, String email, Set<String> roles) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.roles = roles;
    }

}
