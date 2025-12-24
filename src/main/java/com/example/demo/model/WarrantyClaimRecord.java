package com.example.demo.model;

import java.time.LocalDateTime;

public class WarrantyClaimRecord {

    private Long id;
    private String serialNumber;
    private String claimReason;
    private String status = "PENDING";
    private LocalDateTime createdAt = LocalDateTime.now();

    public WarrantyClaimRecord() {}

    // ---------- Builder ----------
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final WarrantyClaimRecord c = new WarrantyClaimRecord();

        public Builder id(Long id) { c.setId(id); return this; }
        public Builder serialNumber(String s) { c.setSerialNumber(s); return this; }
        public Builder claimReason(String r) { c.setClaimReason(r); return this; }
        public Builder status(String s) { c.setStatus(s); return this; }

        public WarrantyClaimRecord build() { return c; }
    }

    // ---------- Getters & Setters ----------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getClaimReason() { return claimReason; }
    public void setClaimReason(String claimReason) { this.claimReason = claimReason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
