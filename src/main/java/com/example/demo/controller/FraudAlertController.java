package com.example.demo.controller;
import com.example.demo.entity.FraudAlertRecord;
import com.example.demo.service.FraudAlertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fraud-alerts")
@Tag(name = "Fraud Alerts")
public class FraudAlertController {

    private final FraudAlertService fraudAlertService;

    public FraudAlertController(FraudAlertService fraudAlertService) {
        this.fraudAlertService = fraudAlertService;
    }

    @PostMapping
    public FraudAlertRecord createAlert(
            @RequestBody FraudAlertRecord alert) {
        return fraudAlertService.createAlert(alert);
    }

    @PutMapping("/{id}/resolve")
    public FraudAlertRecord resolveAlert(@PathVariable Long id) {
        return fraudAlertService.resolveAlert(id);
    }

    @GetMapping("/serial/{serialNumber}")
    public List<FraudAlertRecord> getBySerial(
            @PathVariable String serialNumber) {
        return fraudAlertService.getAlertsBySerial(serialNumber);
    }

    @GetMapping("/claim/{claimId}")
    public List<FraudAlertRecord> getByClaim(
            @PathVariable Long claimId) {
        return fraudAlertService.getAlertsByClaim(claimId);
    }

    @GetMapping
    public List<FraudAlertRecord> getAll() {
        return fraudAlertService.getAllAlerts();
    }
}
