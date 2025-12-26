package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "device_ownership")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceOwnershipRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String serialNumber;
    
    private String ownerName;
    private String ownerEmail;
    
    @Column(nullable = false)
    private LocalDate warrantyExpiration;
    
    @Builder.Default
    private Boolean active = true;
}