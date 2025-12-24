package com.example.demo.dto;

public class AuthResponse {

    private String status;
    private String token;

    public AuthResponse() {
    }

    public AuthResponse(String token) {
        this.token = token;
        this.status = "SUCCESS";
    }

    public AuthResponse(String status, String token) {
        this.status = status;
        this.token = token;
    }


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