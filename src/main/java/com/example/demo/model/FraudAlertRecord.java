package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_alert_records")
public class FraudAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long claimId;
    private String serialNumber;
    private String alertType;
    private String severity;
    private String message;

    private Boolean resolved = false;
    private LocalDateTime alertDate;

    public FraudAlertRecord() {}

    @PrePersist
    void onCreate() {
        alertDate = LocalDateTime.now();
        if (resolved == null) resolved = false;
    }
}
