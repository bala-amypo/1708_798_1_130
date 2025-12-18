package com.example.demo.model;

public class DeviceOwnershipRecord {

    private Long id;
    private String deviceSerial;
    private String ownerEmail;

    public DeviceOwnershipRecord() {
    }

    public DeviceOwnershipRecord(Long id, String deviceSerial, String ownerEmail) {
        this.id = id;
        this.deviceSerial = deviceSerial;
        this.ownerEmail = ownerEmail;
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

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public boolean isEmpty() {
        return id == null && deviceSerial == null && ownerEmail == null;
    }
}
