package com.example.demo.dto;

public class AuthRequest {
    private String email;
    private String password;

    public AuthRequest() {}
    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
}
