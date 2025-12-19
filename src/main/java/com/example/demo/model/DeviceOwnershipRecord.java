package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "device_ownership_records")
public class DeviceOwnershipRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String serialNumber;

    private String ownerName;
    private String ownerEmail;
    private LocalDate purchaseDate;
    private LocalDate warrantyExpiration;

    private Boolean active = true;
    private LocalDateTime createdAt;

    public DeviceOwnershipRecord() {}

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        if (active == null) active = true;
    }
}
