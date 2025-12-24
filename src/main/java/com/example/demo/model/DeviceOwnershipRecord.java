package com.example.demo.model;

import java.time.LocalDate;

public class DeviceOwnershipRecord {

    private Long id;
    private String serialNumber;
    private String ownerName;
    private String ownerEmail;
    private LocalDate warrantyExpiration;
    private Boolean active = true;

    public DeviceOwnershipRecord() {}

    // ---------- Builder ----------
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final DeviceOwnershipRecord d = new DeviceOwnershipRecord();

        public Builder id(Long id) { d.setId(id); return this; }
        public Builder serialNumber(String s) { d.setSerialNumber(s); return this; }
        public Builder ownerName(String o) { d.setOwnerName(o); return this; }
        public Builder ownerEmail(String e) { d.setOwnerEmail(e); return this; }
        public Builder warrantyExpiration(LocalDate w) { d.setWarrantyExpiration(w); return this; }
        public Builder active(Boolean a) { d.setActive(a); return this; }

        public DeviceOwnershipRecord build() { return d; }
    }

    // ---------- Getters & Setters ----------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getOwnerEmail() { return ownerEmail; }
    public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }

    public LocalDate getWarrantyExpiration() { return warrantyExpiration; }
    public void setWarrantyExpiration(LocalDate warrantyExpiration) {
        this.warrantyExpiration = warrantyExpiration;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
