package com.example.demo.controller;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.service.FraudAlertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fraud-alerts")
@Tag(name = "Alert", description = "Fraud alert management")
public class FraudAlertController {
    
    private final FraudAlertService alertService;
    
    public FraudAlertController(FraudAlertService alertService) {
        this.alertService = alertService;
    }
    
    @PostMapping
    public ResponseEntity<FraudAlertRecord> createAlert(@RequestBody FraudAlertRecord alert) {
        return ResponseEntity.ok(alertService.createAlert(alert));
    }
    
    @GetMapping
    public ResponseEntity<List<FraudAlertRecord>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FraudAlertRecord> getAlertById(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<List<FraudAlertRecord>> getAlertsBySerial(@PathVariable String serialNumber) {
        return ResponseEntity.ok(alertService.getAlertsBySerial(serialNumber));
    }
    
    @GetMapping("/claim/{claimId}")
    public ResponseEntity<List<FraudAlertRecord>> getAlertsByClaim(@PathVariable Long claimId) {
        return ResponseEntity.ok(alertService.getAlertsByClaim(claimId));
    }
    
    @PutMapping("/{id}/resolve")
    public ResponseEntity<FraudAlertRecord> resolveAlert(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }
}