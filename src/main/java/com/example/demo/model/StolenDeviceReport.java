package com.example.demo.model;

public class StolenDeviceReport {

    private Long id;
    private String deviceSerial;
    private String reportedBy;

    public StolenDeviceReport() {
    }

    public StolenDeviceReport(Long id, String deviceSerial, String reportedBy) {
        this.id = id;
        this.deviceSerial = deviceSerial;
        this.reportedBy = reportedBy;
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

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public boolean isEmpty() {
        return id == null && deviceSerial == null && reportedBy == null;
    }
}
