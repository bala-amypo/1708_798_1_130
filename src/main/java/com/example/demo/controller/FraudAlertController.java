package com.example.demo.controller;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.service.FraudAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fraud-alerts")
public class FraudAlertController {

    private final FraudAlertService service;

    public FraudAlertController(FraudAlertService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody FraudAlertRecord alert) {
        return ResponseEntity.ok(service.createAlert(alert));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAllAlerts());
    }

    @GetMapping("/serial/{serial}")
    public ResponseEntity<?> getBySerial(@PathVariable String serial) {
        return ResponseEntity.ok(service.getAlertsBySerial(serial));
    }

    @GetMapping("/claim/{claimId}")
    public ResponseEntity<?> getByClaim(@PathVariable Long claimId) {
        return ResponseEntity.ok(service.getAlertsByClaim(claimId));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<?> resolve(@PathVariable Long id) {
        return ResponseEntity.ok(service.resolveAlert(id));
    }
}
