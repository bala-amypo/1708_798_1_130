package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", 
                     joinColumns = @JoinColumn(name = "user_id"),
                     foreignKey = @ForeignKey(name = "FK_user_roles_user"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();
    
    // Add pre-persist to ensure roles are initialized
    @PrePersist
    @PreUpdate
    private void prepareRoles() {
        if (roles == null) {
            roles = new HashSet<>();
        }
        if (roles.isEmpty()) {
            roles.add("USER");
        }
    }
}