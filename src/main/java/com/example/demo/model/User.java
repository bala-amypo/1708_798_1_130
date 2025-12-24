package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();

    // ✅ Required: no-args constructor
    public User() {
        this.roles = new HashSet<>();
    }

    // ---------- Builder ----------
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final User u = new User();

        public Builder id(Long id) {
            u.setId(id);
            return this;
        }

        public Builder name(String name) {
            u.setName(name);
            return this;
        }

        public Builder email(String email) {
            u.setEmail(email);
            return this;
        }

        public Builder password(String password) {
            u.setPassword(password);
            return this;
        }

        public Builder roles(Set<String> roles) {
            if (roles != null) {
                u.setRoles(roles);
            }
            return this;
        }

        public User build() {
            if (u.roles == null) {
                u.roles = new HashSet<>();
            }
            return u;
        }
    }

    // ---------- Getters & Setters ----------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Set<String> getRoles() {
        if (roles == null) roles = new HashSet<>();
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = (roles == null) ? new HashSet<>() : roles;
    }
}
