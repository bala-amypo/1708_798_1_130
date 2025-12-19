package com.example.demo.dto;

import java.util.Set;

public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Set<String> roles;

    public RegisterRequest() {}
    public RegisterRequest(String name, String email, String password, Set<String> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}
