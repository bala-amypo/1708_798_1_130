package com.example.demo.model;

public class FraudRule {

    private Long id;
    private String ruleName;
    private String description;

    public FraudRule() {
    }

    public FraudRule(Long id, String ruleName, String description) {
        this.id = id;
        this.ruleName = ruleName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
