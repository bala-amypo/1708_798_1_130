package com.example.demo.model;

public class FraudAlertRecord {

    private Long id;
    private String alertMessage;
    private String deviceSerial;

    public FraudAlertRecord() {
    }

    public FraudAlertRecord(Long id, String alertMessage, String deviceSerial) {
        this.id = id;
        this.alertMessage = alertMessage;
        this.deviceSerial = deviceSerial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public void setDeviceSerial(String deviceSerial) {
        this.deviceSerial = deviceSerial;
    }
}
