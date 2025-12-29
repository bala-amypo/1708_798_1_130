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
    
    @Column(name = "alert_date")
    private LocalDateTime alertDate;
    
    private Boolean resolved = false;
    
    public FraudAlertRecord() {}
    
    public FraudAlertRecord(Long claimId, String serialNumber, String alertType, String severity, String message) {
        this.claimId = claimId;
        this.serialNumber = serialNumber;
        this.alertType = alertType;
        this.severity = severity;
        this.message = message;
        this.resolved = false;
    }
    
    @PrePersist
    protected void onCreate() {
        alertDate = LocalDateTime.now();
    }
    
    public static FraudAlertRecordBuilder builder() {
        return new FraudAlertRecordBuilder();
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getClaimId() { return claimId; }
    public void setClaimId(Long claimId) { this.claimId = claimId; }
    
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    
    public String getAlertType() { return alertType; }
    public void setAlertType(String alertType) { this.alertType = alertType; }
    
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public LocalDateTime getAlertDate() { return alertDate; }
    public void setAlertDate(LocalDateTime alertDate) { this.alertDate = alertDate; }
    
    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
    
    public static class FraudAlertRecordBuilder {
        private Long id;
        private Long claimId;
        private String serialNumber;
        private String alertType;
        private String severity;
        private String message;
        private Boolean resolved = false;
        
        public FraudAlertRecordBuilder id(Long id) { this.id = id; return this; }
        public FraudAlertRecordBuilder claimId(Long claimId) { this.claimId = claimId; return this; }
        public FraudAlertRecordBuilder serialNumber(String serialNumber) { this.serialNumber = serialNumber; return this; }
        public FraudAlertRecordBuilder alertType(String alertType) { this.alertType = alertType; return this; }
        public FraudAlertRecordBuilder severity(String severity) { this.severity = severity; return this; }
        public FraudAlertRecordBuilder message(String message) { this.message = message; return this; }
        public FraudAlertRecordBuilder resolved(Boolean resolved) { this.resolved = resolved; return this; }
        
        public FraudAlertRecord build() {
            FraudAlertRecord alert = new FraudAlertRecord();
            alert.id = this.id;
            alert.claimId = this.claimId;
            alert.serialNumber = this.serialNumber;
            alert.alertType = this.alertType;
            alert.severity = this.severity;
            alert.message = this.message;
            alert.resolved = this.resolved;
            return alert;
        }
    }
}