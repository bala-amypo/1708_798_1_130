package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stolen_device_reports")
public class StolenDeviceReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private String reportedBy;

    private String details;

    private LocalDateTime reportDate;

    public StolenDeviceReport() {
    }

    private StolenDeviceReport(Builder builder) {
        this.id = builder.id;
        this.serialNumber = builder.serialNumber;
        this.reportedBy = builder.reportedBy;
        this.details = builder.details;
        this.reportDate = builder.reportDate;
    }

    @PrePersist
    public void onCreate() {
        if (this.reportDate == null) {
            this.reportDate = LocalDateTime.now();
        }
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String serialNumber;
        private String reportedBy;
        private String details;
        private LocalDateTime reportDate;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public Builder reportedBy(String reportedBy) {
            this.reportedBy = reportedBy;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder reportDate(LocalDateTime reportDate) {
            this.reportDate = reportDate;
            return this;
        }

        public StolenDeviceReport build() {
            return new StolenDeviceReport(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }
}