package com.example.demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")   
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();


    public User() {}

 
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
            return u;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { ret+urn password; }
    public void setPassword(String password) { this.password = password; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) {
        this.roles = (roles == null) ? new HashSet<>() : roles;
    }
}
