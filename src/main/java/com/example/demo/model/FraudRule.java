package com.example.demo.model;

public class FraudRule {

    private Long id;
    private String ruleCode;
    private String ruleType;
    private String description;
    private Boolean active = true;

    public FraudRule() {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final FraudRule r = new FraudRule();

        public Builder id(Long id) { r.setId(id); return this; }
        public Builder ruleCode(String c) { r.setRuleCode(c); return this; }
        public Builder ruleType(String t) { r.setRuleType(t); return this; }
        public Builder description(String d) { r.setDescription(d); return this; }
        public Builder active(Boolean a) { r.setActive(a); return this; }

        public FraudRule build() { return r; }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public String getRuleType() { return ruleType; }
    public void setRuleType(String ruleType) { this.ruleType = ruleType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
