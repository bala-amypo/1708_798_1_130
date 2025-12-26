package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "stolen_devices")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StolenDeviceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String serialNumber;
    
    private LocalDate reportDate;
    private String reportDetails;
}