package com.example.demo.model;

public class FraudAlertRecord {

    private Long id;
    private Long claimId;
    private String serialNumber;
    private Boolean resolved = false;

    public FraudAlertRecord() {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final FraudAlertRecord a = new FraudAlertRecord();

        public Builder id(Long id) { a.setId(id); return this; }
        public Builder claimId(Long c) { a.setClaimId(c); return this; }
        public Builder serialNumber(String s) { a.setSerialNumber(s); return this; }
        public Builder resolved(Boolean r) { a.setResolved(r); return this; }

        public FraudAlertRecord build() { return a; }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getClaimId() { return claimId; }
    public void setClaimId(Long claimId) { this.claimId = claimId; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
}
