package com.example.demo.dto;

public class AuthResponse {

    private String message;
    private String token;

    // ✅ REQUIRED NO-ARG CONSTRUCTOR (Spring + Jackson)
    public AuthResponse() {
    }

    // ✅ REQUIRED BY UserServiceImpl & TEST CASES
    public AuthResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    // ===== GETTERS & SETTERS =====

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
