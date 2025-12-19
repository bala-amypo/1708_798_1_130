package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "warranty_claim_records")
public class WarrantyClaimRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private String claimantName;

    private String claimantEmail;

    @Column(nullable = false)
    private String claimReason;

    private LocalDateTime submittedAt;
    private String status = "PENDING";
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceOwnershipRecord deviceOwnershipRecord;

    @OneToMany(mappedBy = "warrantyClaimRecord")
    private List<FraudAlertRecord> fraudAlerts;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        submittedAt = LocalDateTime.now();
    }

    public WarrantyClaimRecord() {}
    public WarrantyClaimRecord(String serialNumber, String claimantName, String claimReason) {
        this.serialNumber = serialNumber;
        this.claimantName = claimantName;
        this.claimReason = claimReason;
    }

}
