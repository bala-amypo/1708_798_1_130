package com.example.demo.model;

public class WarrantyClaimRecord {

    private Long id;
    private String deviceSerial;
    private String claimStatus;

    public WarrantyClaimRecord() {
    }

    public WarrantyClaimRecord(Long id, String deviceSerial, String claimStatus) {
        this.id = id;
        this.deviceSerial = deviceSerial;
        this.claimStatus = claimStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public void setDeviceSerial(String deviceSerial) {
        this.deviceSerial = deviceSerial;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public boolean isEmpty() {
        return id == null && deviceSerial == null && claimStatus == null;
    }
}
