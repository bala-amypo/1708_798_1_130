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

    /* ================= CONSTRUCTORS ================= */

    // REQUIRED by JPA
    public User() {
    }

    // REQUIRED by UserServiceImpl
    public User(String name,
                String email,
                String password,
                Set<String> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }


    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.roles == null || this.roles.isEmpty()) {
            this.roles = Set.of("USER");
        }
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRoles() {
        return roles;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    /* ================= BUILDER (TEST REQUIRED) ================= */

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final User u = new User();

        public Builder id(Long id) {
            u.id = id;
            return this;
        }

        public Builder name(String name) {
            u.name = name;
            return this;
        }

        public Builder email(String email) {
            u.email = email;
            return this;
        }

        public Builder password(String password) {
            u.password = password;
            return this;
        }

        public Builder roles(Set<String> roles) {
            u.roles = roles;
            return this;
        }

        public User build() {
            return u;
        }
    }
}
