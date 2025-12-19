package com.example.demo.controller;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.service.FraudAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fraud-alerts")
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
    public ResponseEntity<FraudAlertRecord> getAlert(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.getAllAlerts().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Request not found")));
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<List<FraudAlertRecord>> getBySerial(@PathVariable String serialNumber) {
        return ResponseEntity.ok(alertService.getAlertsBySerial(serialNumber));
    }

    @GetMapping("/claim/{claimId}")
    public ResponseEntity<List<FraudAlertRecord>> getByClaim(@PathVariable Long claimId) {
        return ResponseEntity.ok(alertService.getAlertsByClaim(claimId));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<FraudAlertRecord> resolveAlert(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }
}
