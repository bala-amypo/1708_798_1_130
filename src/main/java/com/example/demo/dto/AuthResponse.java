package com.example.demo.dto;

import java.util.Set;

public class AuthResponse {
    private String token;
    private String email;
    private String name;
    private Set<String> roles;
    
    // No-args constructor
    public AuthResponse() {}
    
    // All-args constructor
    public AuthResponse(String token, String email, String name, Set<String> roles) {
        this.token = token;
        this.email = email;
        this.name = name;
        this.roles = roles;
    }
    
    // Builder pattern manually
    public static AuthResponseBuilder builder() {
        return new AuthResponseBuilder();
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
    
    // Builder class
    public static class AuthResponseBuilder {
        private String token;
        private String email;
        private String name;
        private Set<String> roles;
        
        public AuthResponseBuilder token(String token) {
            this.token = token;
            return this;
        }
        
        public AuthResponseBuilder email(String email) {
            this.email = email;
            return this;
        }
        
        public AuthResponseBuilder name(String name) {
            this.name = name;
            return this;
        }
        
        public AuthResponseBuilder roles(Set<String> roles) {
            this.roles = roles;
            return this;
        }
        
        public AuthResponse build() {
            return new AuthResponse(token, email, name, roles);
        }
    }
}