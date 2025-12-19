package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "device_ownership_records")
public class DeviceOwnershipRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String serialNumber;

    @Column(nullable = false)
    private String ownerName;

    private String ownerEmail;
    private LocalDate purchaseDate;

    @Column(nullable = false)
    private LocalDate warrantyExpiration;

    private boolean active = true;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "deviceOwnershipRecord")
    private List<WarrantyClaimRecord> warrantyClaims;

    @OneToMany(mappedBy = "deviceOwnershipRecord")
    private List<StolenDeviceReport> stolenReports;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public DeviceOwnershipRecord() {}
    public DeviceOwnershipRecord(String serialNumber, String ownerName, LocalDate warrantyExpiration) {
        this.serialNumber = serialNumber;
        this.ownerName = ownerName;
        this.warrantyExpiration = warrantyExpiration;
    }

}
