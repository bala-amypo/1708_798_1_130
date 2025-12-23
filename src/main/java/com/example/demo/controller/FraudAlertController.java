package com.example.demo.controller;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.service.FraudAlertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fraud-alerts")
@Tag(name = "Alert")
public class FraudAlertController {

    private final FraudAlertService alertService;

    public FraudAlertController(FraudAlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<FraudAlertRecord> createAlert(
            @RequestBody FraudAlertRecord alert) {
        return ResponseEntity.ok(alertService.createAlert(alert));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<FraudAlertRecord> resolveAlert(
            @PathVariable Long id) {
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<List<FraudAlertRecord>> getBySerial(
            @PathVariable String serialNumber) {
        return ResponseEntity.ok(alertService.getAlertsBySerial(serialNumber));
    }

    @GetMapping("/claim/{claimId}")
    public ResponseEntity<List<FraudAlertRecord>> getByClaim(
            @PathVariable Long claimId) {
        return ResponseEntity.ok(alertService.getAlertsByClaim(claimId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FraudAlertRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                alertService.getById(id)
                        .orElseThrow(() -> new java.util.NoSuchElementException("Request not found"))
        );
    }

    @GetMapping
    public ResponseEntity<List<FraudAlertRecord>> getAll() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }
}
