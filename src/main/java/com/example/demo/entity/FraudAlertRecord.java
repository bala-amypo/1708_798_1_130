package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_alerts")
public class FraudAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long claimId;

    private String serialNumber;

    private String alertType;

    private String severity;

    private String message;

    private LocalDateTime alertDate;

    private Boolean resolved = false;

    @PrePersist
    public void onCreate() {
        this.alertDate = LocalDateTime.now();
        this.resolved = false;
    }


    public Long getId() {
        return id;
    }

    public Long getClaimId() {
        return claimId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getAlertType() {
        return alertType;
    }

    public String getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getAlertDate() {
        return alertDate;
    }

    public Boolean getResolved() {
        return resolved;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAlertDate(LocalDateTime alertDate) {
        this.alertDate = alertDate;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }
}
