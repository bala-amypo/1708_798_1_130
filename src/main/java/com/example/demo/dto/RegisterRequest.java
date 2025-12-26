package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
    private Set<String> roles;
}