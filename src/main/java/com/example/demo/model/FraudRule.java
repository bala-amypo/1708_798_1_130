package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_rules")
public class FraudRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;
    private String ruleType;
    private String description;
    private Boolean active = true;
    private LocalDateTime createdAt;

    public FraudRule() {}

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String r) { this.ruleCode = r; }

    public String getRuleType() { return ruleType; }
    public void setRuleType(String r) { this.ruleType = r; }

    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean a) { this.active = a; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FraudRule f = new FraudRule();
        public Builder id(Long id) { f.id = id; return this; }
        public Builder ruleCode(String r) { f.ruleCode = r; return this; }
        public Builder ruleType(String r) { f.ruleType = r; return this; }
        public Builder active(Boolean a) { f.active = a; return this; }
        public FraudRule build() { return f; }
    }
}
