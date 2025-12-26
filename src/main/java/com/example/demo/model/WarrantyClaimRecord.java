package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "warranty_claims")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarrantyClaimRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String serialNumber;
    
    private String claimReason;
    
    @Builder.Default
    private String status = "PENDING";
    
    @Builder.Default
    private LocalDateTime claimDate = LocalDateTime.now();
}