package com.example.demo.controller;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.service.FraudAlertService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class FraudAlertController {

    private final FraudAlertService alertService;

    public FraudAlertController(FraudAlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<FraudAlertRecord> create(
            @RequestBody FraudAlertRecord alert) {
        return ResponseEntity.ok(alertService.createAlert(alert));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<FraudAlertRecord> resolve(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }

    @GetMapping("/claim/{claimId}")
    public List<FraudAlertRecord> byClaim(@PathVariable Long claimId) {
        return alertService.getAlertsByClaim(claimId);
    }
}
