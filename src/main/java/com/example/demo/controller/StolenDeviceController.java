package com.example.demo.controller;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.service.StolenDeviceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stolen-devices")
@Tag(name = "StolenDevice")
public class StolenDeviceController {

    private final StolenDeviceService service;

    public StolenDeviceController(StolenDeviceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StolenDeviceReport> report(
            @RequestBody StolenDeviceReport report) {
        return ResponseEntity.ok(service.reportStolen(report));
    }

    @GetMapping
    public ResponseEntity<List<StolenDeviceReport>> getAll() {
        return ResponseEntity.ok(service.getAllReports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StolenDeviceReport> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                service.getReportById(id)
                        .orElseThrow(() ->
                                new java.util.NoSuchElementException("Request not found")));
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<List<StolenDeviceReport>> getBySerial(
            @PathVariable String serialNumber) {
        return ResponseEntity.ok(service.getReportsBySerial(serialNumber));
    }
}
