package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_rules")
public class FraudRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleCode;

    @Column(nullable = false)
    private String ruleType;

    private String description;
    private boolean active = true;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public FraudRule() {}
    public FraudRule(String ruleCode, String ruleType) {
        this.ruleCode = ruleCode;
        this.ruleType = ruleType;
    }

}
