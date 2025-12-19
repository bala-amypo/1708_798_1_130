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

    private LocalDateTime reportDate;

    private String details;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceOwnershipRecord deviceOwnershipRecord;

    @PrePersist
    public void prePersist() {
        reportDate = LocalDateTime.now();
    }

    public StolenDeviceReport() {}
    public StolenDeviceReport(String serialNumber, String reportedBy) {
        this.serialNumber = serialNumber;
        this.reportedBy = reportedBy;
    }

}
