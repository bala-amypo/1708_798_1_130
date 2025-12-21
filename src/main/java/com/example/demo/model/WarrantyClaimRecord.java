package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "warranty_claim_records")
public class WarrantyClaimRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceOwnershipRecord device;
    
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;
    
    @Column(name = "claimant_name", nullable = false)
    private String claimantName;
    
    @Column(name = "claimant_email")
    private String claimantEmail;
    
    @Column(name = "claim_reason", nullable = false)
    private String claimReason;
    
    @Column(name = "submitted_at", updatable = false)
    private LocalDateTime submittedAt;
    
    @Column(name = "status")
    private String status = "PENDING";
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL)
    private List<FraudAlertRecord> fraudAlerts = new ArrayList<>();
    
    public WarrantyClaimRecord() {}
    
    public WarrantyClaimRecord(DeviceOwnershipRecord device, String claimantName, 
                              String claimantEmail, String claimReason) {
        this.device = device;
        this.serialNumber = device.getSerialNumber();
        this.claimantName = claimantName;
        this.claimantEmail = claimantEmail;
        this.claimReason = claimReason;
    }
    
    @PrePersist
    protected void onCreate() {
        submittedAt = LocalDateTime.now();
        createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public DeviceOwnershipRecord getDevice() { return device; }
    public void setDevice(DeviceOwnershipRecord device) { 
        this.device = device; 
        this.serialNumber = device.getSerialNumber();
    }
    
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    
    public String getClaimantName() { return claimantName; }
    public void setClaimantName(String claimantName) { this.claimantName = claimantName; }
    
    public String getClaimantEmail() { return claimantEmail; }
    public void setClaimantEmail(String claimantEmail) { this.claimantEmail = claimantEmail; }
    
    public String getClaimReason() { return claimReason; }
    public void setClaimReason(String claimReason) { this.claimReason = claimReason; }
    
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public List<FraudAlertRecord> getFraudAlerts() { return fraudAlerts; }
    public void setFraudAlerts(List<FraudAlertRecord> fraudAlerts) { this.fraudAlerts = fraudAlerts; }
}