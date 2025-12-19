package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_rules")
public class FraudRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ruleCode;

    private String ruleType;
    private String description;
    private Boolean active = true;
    private LocalDateTime createdAt;

    public FraudRule() {}

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        if (active == null) active = true;
    }
}
