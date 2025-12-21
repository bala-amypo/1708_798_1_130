package com.example.demo.controller;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.service.FraudAlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/fraud-alerts")
@Tag(name = "Fraud Alerts", description = "Fraud alert management endpoints")
@SecurityRequirement(name = "bearerAuth")
public class FraudAlertController {
    
    private final FraudAlertService fraudAlertService;
    
    public FraudAlertController(FraudAlertService fraudAlertService) {
        this.fraudAlertService = fraudAlertService;
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a fraud alert")
    public ResponseEntity<FraudAlertRecord> createAlert(@RequestBody FraudAlertRecord alert) {
        FraudAlertRecord createdAlert = fraudAlertService.createAlert(alert);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlert);
    }
    
    @GetMapping
    @Operation(summary = "Get all fraud alerts")
    public ResponseEntity<List<FraudAlertRecord>> getAllAlerts() {
        List<FraudAlertRecord> alerts = fraudAlertService.getAllAlerts();
        return ResponseEntity.ok(alerts);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get fraud alert by ID")
    public ResponseEntity<?> getAlertById(@PathVariable Long id) {
        try {
            // Simplified - would need a getById method in service
            List<FraudAlertRecord> allAlerts = fraudAlertService.getAllAlerts();
            return allAlerts.stream()
                    .filter(alert -> alert.getId().equals(id))
                    .findFirst()
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/serial/{serialNumber}")
    @Operation(summary = "Get fraud alerts by serial number")
    public ResponseEntity<List<FraudAlertRecord>> getAlertsBySerial(@PathVariable String serialNumber) {
        List<FraudAlertRecord> alerts = fraudAlertService.getAlertsBySerial(serialNumber);
        return ResponseEntity.ok(alerts);
    }
    
    @GetMapping("/claim/{claimId}")
    @Operation(summary = "Get fraud alerts by claim ID")
    public ResponseEntity<List<FraudAlertRecord>> getAlertsByClaim(@PathVariable Long claimId) {
        List<FraudAlertRecord> alerts = fraudAlertService.getAlertsByClaim(claimId);
        return ResponseEntity.ok(alerts);
    }
    
    @PutMapping("/{id}/resolve")
    @PreAuthorize("hasRole('ADMIN') or hasRole('FRAUDANALYST')")
    @Operation(summary = "Resolve a fraud alert")
    public ResponseEntity<?> resolveAlert(@PathVariable Long id) {
        try {
            FraudAlertRecord resolvedAlert = fraudAlertService.resolveAlert(id);
            return ResponseEntity.ok(resolvedAlert);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}