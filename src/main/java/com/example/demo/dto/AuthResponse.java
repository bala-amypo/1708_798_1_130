package com.example.demo.dto;

public class AuthResponse {

    private String status;
    private String token;

    // ✅ REQUIRED: no-arg constructor (Spring / Jackson)
    public AuthResponse() {
    }

    // ✅ REQUIRED: single-arg constructor (Controller usage)
    public AuthResponse(String token) {
        this.token = token;
        this.status = "SUCCESS";
    }

    // ✅ REQUIRED: two-arg constructor (TestNG usage)
    public AuthResponse(String status, String token) {
        this.status = status;
        this.token = token;
    }

    // ===== getters & setters =====

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
