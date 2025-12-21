package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_alert_records")
public class FraudAlertRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "claim_id", nullable = false)
    private WarrantyClaimRecord claim;
    
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;
    
    @Column(name = "alert_type", nullable = false)
    private String alertType;
    
    @Column(name = "severity", nullable = false)
    private String severity;
    
    @Column(name = "message")
    private String message;
    
    @Column(name = "alert_date", updatable = false)
    private LocalDateTime alertDate;
    
    @Column(name = "resolved")
    private Boolean resolved = false;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignedUser;
    
    public FraudAlertRecord() {}
    
    public FraudAlertRecord(WarrantyClaimRecord claim, String alertType, 
                           String severity, String message) {
        this.claim = claim;
        this.serialNumber = claim.getSerialNumber();
        this.alertType = alertType;
        this.severity = severity;
        this.message = message;
    }
    
    @PrePersist
    protected void onCreate() {
        alertDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public WarrantyClaimRecord getClaim() { return claim; }
    public void setClaim(WarrantyClaimRecord claim) { 
        this.claim = claim; 
        this.serialNumber = claim.getSerialNumber();
    }
    
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
    
    public User getAssignedUser() { return assignedUser; }
    public void setAssignedUser(User assignedUser) { this.assignedUser = assignedUser; }
}