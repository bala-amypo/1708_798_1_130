package com.example.demo.controller;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.service.StolenDeviceService;
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
@RequestMapping("/api/stolen-devices")
@Tag(name = "Stolen Devices", description = "Stolen device report management endpoints")
@SecurityRequirement(name = "bearerAuth")
public class StolenDeviceController {
    
    private final StolenDeviceService stolenDeviceService;
    
    public StolenDeviceController(StolenDeviceService stolenDeviceService) {
        this.stolenDeviceService = stolenDeviceService;
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Report a stolen device")
    public ResponseEntity<?> reportStolen(@RequestBody StolenDeviceReport report) {
        try {
            StolenDeviceReport savedReport = stolenDeviceService.reportStolen(report);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReport);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping
    @Operation(summary = "Get all stolen device reports")
    public ResponseEntity<List<StolenDeviceReport>> getAllReports() {
        List<StolenDeviceReport> reports = stolenDeviceService.getAllReports();
        return ResponseEntity.ok(reports);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get stolen report by ID")
    public ResponseEntity<?> getReportById(@PathVariable Long id) {
        return stolenDeviceService.getReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/serial/{serialNumber}")
    @Operation(summary = "Get stolen reports by serial number")
    public ResponseEntity<List<StolenDeviceReport>> getReportsBySerial(@PathVariable String serialNumber) {
        List<StolenDeviceReport> reports = stolenDeviceService.getReportsBySerial(serialNumber);
        return ResponseEntity.ok(reports);
    }
}