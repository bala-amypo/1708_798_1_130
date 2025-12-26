package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_alerts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FraudAlertRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String serialNumber;
    private Long claimId;
    private String alertReason;
    
    @Builder.Default
    private LocalDateTime alertDate = LocalDateTime.now();
    
    @Builder.Default
    private Boolean resolved = false;
}