package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "warranty_claim_records")
public class WarrantyClaimRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String serialNumber;
    
    @Column(nullable = false)
    private String claimantName;
    
    private String claimantEmail;
    
    @Column(nullable = false)
    private String claimReason;
    
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
    
    private String status = "PENDING";
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    public WarrantyClaimRecord() {}
    
    public WarrantyClaimRecord(String serialNumber, String claimantName, String claimantEmail, String claimReason) {
        this.serialNumber = serialNumber;
        this.claimantName = claimantName;
        this.claimantEmail = claimantEmail;
        this.claimReason = claimReason;
        this.status = "PENDING";
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        submittedAt = LocalDateTime.now();
    }
    
    public static WarrantyClaimRecordBuilder builder() {
        return new WarrantyClaimRecordBuilder();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
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
    
    public static class WarrantyClaimRecordBuilder {
        private Long id;
        private String serialNumber;
        private String claimantName;
        private String claimantEmail;
        private String claimReason;
        private String status = "PENDING";
        
        public WarrantyClaimRecordBuilder id(Long id) { this.id = id; return this; }
        public WarrantyClaimRecordBuilder serialNumber(String serialNumber) { this.serialNumber = serialNumber; return this; }
        public WarrantyClaimRecordBuilder claimantName(String claimantName) { this.claimantName = claimantName; return this; }
        public WarrantyClaimRecordBuilder claimantEmail(String claimantEmail) { this.claimantEmail = claimantEmail; return this; }
        public WarrantyClaimRecordBuilder claimReason(String claimReason) { this.claimReason = claimReason; return this; }
        public WarrantyClaimRecordBuilder status(String status) { this.status = status; return this; }
        
        public WarrantyClaimRecord build() {
            WarrantyClaimRecord record = new WarrantyClaimRecord();
            record.id = this.id;
            record.serialNumber = this.serialNumber;
            record.claimantName = this.claimantName;
            record.claimantEmail = this.claimantEmail;
            record.claimReason = this.claimReason;
            record.status = this.status;
            return record;
        }
    }
}