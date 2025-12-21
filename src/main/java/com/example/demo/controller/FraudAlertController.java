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

    private final FraudAlertService service;

    public FraudAlertController(FraudAlertService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FraudAlertRecord> create(
            @RequestBody FraudAlertRecord alert) {
        return ResponseEntity.ok(service.createAlert(alert));
    }

    @GetMapping
    public ResponseEntity<List<FraudAlertRecord>> getAll() {
        return ResponseEntity.ok(service.getAllAlerts());
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<List<FraudAlertRecord>> getBySerial(
            @PathVariable String serialNumber) {
        return ResponseEntity.ok(service.getAlertsBySerial(serialNumber));
    }

    @GetMapping("/claim/{claimId}")
    public ResponseEntity<List<FraudAlertRecord>> getByClaim(
            @PathVariable Long claimId) {
        return ResponseEntity.ok(service.getAlertsByClaim(claimId));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<FraudAlertRecord> resolve(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.resolveAlert(id));
    }
}
