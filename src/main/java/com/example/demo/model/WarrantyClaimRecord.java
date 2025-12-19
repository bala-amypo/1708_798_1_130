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
    private String status = "PENDING";
    private LocalDateTime submittedAt;
    private LocalDateTime createdAt;

    public WarrantyClaimRecord() {}

    @PrePersist
    void onCreate() {
        submittedAt = LocalDateTime.now();
        createdAt = LocalDateTime.now();
    }

    /* ===== GETTERS / SETTERS ===== */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String s) { this.serialNumber = s; }

    public String getClaimReason() { return claimReason; }
    public void setClaimReason(String c) { this.claimReason = c; }

    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }

    /* ===== BUILDER ===== */
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final WarrantyClaimRecord w = new WarrantyClaimRecord();
        public Builder id(Long id) { w.id = id; return this; }
        public Builder serialNumber(String s) { w.serialNumber = s; return this; }
        public Builder claimReason(String c) { w.claimReason = c; return this; }
        public Builder status(String s) { w.status = s; return this; }
        public WarrantyClaimRecord build() { return w; }
    }
}
