package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    private LocalDateTime createdAt;

    public User() {}

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        if (roles == null || roles.isEmpty()) {
            roles = Set.of("USER");
        }
    }

    /* ===== GETTERS / SETTERS ===== */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final User u = new User();
        public Builder id(Long id) { u.id = id; return this; }
        public Builder name(String name) { u.name = name; return this; }
        public Builder email(String email) { u.email = email; return this; }
        public Builder password(String password) { u.password = password; return this; }
        public Builder roles(Set<String> roles) { u.roles = roles; return this; }
        public User build() { return u; }
    }
}
