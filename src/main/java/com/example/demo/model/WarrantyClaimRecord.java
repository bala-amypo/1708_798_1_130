package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warranty_claim_records")
public class WarrantyClaimRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;
    private String claimantName;
    private String claimantEmail;
    private String claimReason;
    private LocalDateTime submittedAt;
    private String status = "PENDING";
    private LocalDateTime createdAt;

    public WarrantyClaimRecord() {
    }

    public WarrantyClaimRecord(String serialNumber, String claimantName,
                               String claimantEmail, String claimReason) {
        this.serialNumber = serialNumber;
        this.claimantName = claimantName;
        this.claimantEmail = claimantEmail;
        this.claimReason = claimReason;
    }

    @PrePersist
    protected void onCreate() {
        this.submittedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSerialNumber() {
        return serialNumber;
    }
    
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public String getClaimantName() {
        return claimantName;
    }
    
    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }
    
    public String getClaimantEmail() {
        return claimantEmail;
    }
    
    public void setClaimantEmail(String claimantEmail) {
        this.claimantEmail = claimantEmail;
    }
    
    public String getClaimReason() {
        return claimReason;
    }
    
    public void setClaimReason(String claimReason) {
        this.claimReason = claimReason;
    }
    
    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
    
    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
