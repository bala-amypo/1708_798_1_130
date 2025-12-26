package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fraud_rules")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FraudRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String ruleCode;
    
    private String ruleType;
    private String description;
    
    @Builder.Default
    private Boolean active = true;
}