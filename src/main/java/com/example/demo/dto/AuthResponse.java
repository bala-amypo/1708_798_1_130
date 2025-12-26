package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String email;
    private String name;
    private Set<String> roles;
    
    // If you're not using Lombok, create these constructors manually:
    public AuthResponse() {}
    
    public AuthResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }
    
    public AuthResponse(String token, String email, String name, Set<String> roles) {
        this.token = token;
        this.email = email;
        this.name = name;
        this.roles = roles;
    }
    
    // Getters and setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}