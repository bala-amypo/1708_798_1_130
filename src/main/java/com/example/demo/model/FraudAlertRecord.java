package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_alert_records")
public class FraudAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long claimId;

    @Column(nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private String alertType;

    @Column(nullable = false)
    private String severity;

    private String message;

    private LocalDateTime alertDate;

    private Boolean resolved = false;


    public FraudAlertRecord() {
    }

    private FraudAlertRecord(Builder builder) {
        this.id = builder.id;
        this.claimId = builder.claimId;
        this.serialNumber = builder.serialNumber;
        this.alertType = builder.alertType;
        this.severity = builder.severity;
        this.message = builder.message;
        this.alertDate = builder.alertDate;
        this.resolved = builder.resolved;
    }

    @PrePersist
    public void onCreate() {
        if (this.alertDate == null) {
            this.alertDate = LocalDateTime.now();
        }
        if (this.resolved == null) {
            this.resolved = false;
        }
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Long claimId;
        private String serialNumber;
        private String alertType;
        private String severity;
        private String message;
        private LocalDateTime alertDate;
        private Boolean resolved;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder claimId(Long claimId) {
            this.claimId = claimId;
            return this;
        }

        public Builder serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public Builder alertType(String alertType) {
            this.alertType = alertType;
            return this;
        }

        public Builder severity(String severity) {
            this.severity = severity;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder alertDate(LocalDateTime alertDate) {
            this.alertDate = alertDate;
            return this;
        }

        public Builder resolved(Boolean resolved) {
            this.resolved = resolved;
            return this;
        }

        public FraudAlertRecord build() {
            return new FraudAlertRecord(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(LocalDateTime alertDate) {
        this.alertDate = alertDate;
    }

    public Boolean getResolved() {
        return resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }
}