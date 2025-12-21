package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stolen_device_reports")
public class StolenDeviceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceOwnershipRecord device;
    
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;
    
    @Column(name = "reported_by", nullable = false)
    private String reportedBy;
    
    @Column(name = "report_date", updatable = false)
    private LocalDateTime reportDate;
    
    @Column(name = "details")
    private String details;
    
    public StolenDeviceReport() {}
    
    public StolenDeviceReport(DeviceOwnershipRecord device, String reportedBy, String details) {
        this.device = device;
        this.serialNumber = device.getSerialNumber();
        this.reportedBy = reportedBy;
        this.details = details;
    }
    
    @PrePersist
    protected void onCreate() {
        reportDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public DeviceOwnershipRecord getDevice() { return device; }
    public void setDevice(DeviceOwnershipRecord device) { 
        this.device = device; 
        this.serialNumber = device.getSerialNumber();
    }
    
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    
    public String getReportedBy() { return reportedBy; }
    public void setReportedBy(String reportedBy) { this.reportedBy = reportedBy; }
    
    public LocalDateTime getReportDate() { return reportDate; }
    public void setReportDate(LocalDateTime reportDate) { this.reportDate = reportDate; }
    
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}