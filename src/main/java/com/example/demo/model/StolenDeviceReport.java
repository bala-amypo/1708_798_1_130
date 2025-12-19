package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stolen_device_reports")
public class StolenDeviceReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;
    private String reportedBy;
    private String details;
    private LocalDateTime reportDate;

    public StolenDeviceReport() {}

    @PrePersist
    void onCreate() {
        reportDate = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String s) { this.serialNumber = s; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final StolenDeviceReport s = new StolenDeviceReport();
        public Builder id(Long id) { s.id = id; return this; }
        public Builder serialNumber(String sn) { s.serialNumber = sn; return this; }
        public Builder reportedBy(String r) { s.reportedBy = r; return this; }
        public StolenDeviceReport build() { return s; }
    }
}
