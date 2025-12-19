package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_alert_records")
public class FraudAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long claimId;
    private String serialNumber;
    private String alertType;
    private String severity;
    private Boolean resolved = false;
    private LocalDateTime alertDate;

    public FraudAlertRecord() {}

    @PrePersist
    void onCreate() {
        alertDate = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean r) { this.resolved = r; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FraudAlertRecord f = new FraudAlertRecord();
        public Builder id(Long id) { f.id = id; return this; }
        public Builder claimId(Long c) { f.claimId = c; return this; }
        public Builder serialNumber(String s) { f.serialNumber = s; return this; }
        public Builder severity(String s) { f.severity = s; return this; }
        public FraudAlertRecord build() { return f; }
    }
}
