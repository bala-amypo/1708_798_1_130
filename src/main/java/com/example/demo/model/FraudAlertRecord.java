package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_alert_records")
public class FraudAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long claimId;

    @Column(nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private String alertType;

    @Column(nullable = false)
    private String severity;

    private String message;
    private LocalDateTime alertDate;
    private boolean resolved = false;

    @ManyToOne
    @JoinColumn(name = "claim_id", insertable = false, updatable = false)
    private WarrantyClaimRecord warrantyClaimRecord;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void prePersist() {
        alertDate = LocalDateTime.now();
    }

    public FraudAlertRecord() {}
    public FraudAlertRecord(Long claimId, String serialNumber, String alertType, String severity) {
        this.claimId = claimId;
        this.serialNumber = serialNumber;
        this.alertType = alertType;
        this.severity = severity;
    }

}
