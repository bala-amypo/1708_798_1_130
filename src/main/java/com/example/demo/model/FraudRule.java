package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "fraud_rules",
    uniqueConstraints = @UniqueConstraint(columnNames = "ruleCode")
)
public class FraudRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleCode;

    @Column(nullable = false)
    private String ruleType;

    private String description;

    private Boolean active = true;

    private LocalDateTime createdAt;

    public FraudRule() {}

    public FraudRule(String ruleCode, String ruleType) {
        this.ruleCode = ruleCode;
        this.ruleType = ruleType;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.active == null) {
            this.active = true;
        }
    }

    public Long getId() {
        return id;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public String getRuleType() {
        return ruleType;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getActive() {
        return active;
    }


    public void setActive(Boolean active) {
        this.active = active;
    }
}
