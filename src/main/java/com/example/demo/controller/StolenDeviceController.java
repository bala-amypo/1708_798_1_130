package com.example.demo.controller;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.service.StolenDeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stolen-devices")
public class StolenDeviceController {

    private final StolenDeviceService service;

    public StolenDeviceController(StolenDeviceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> report(@RequestBody StolenDeviceReport report) {
        return ResponseEntity.ok(service.reportStolen(report));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAllReports());
    }

    @GetMapping("/serial/{serial}")
    public ResponseEntity<?> getBySerial(@PathVariable String serial) {
        return ResponseEntity.ok(service.getReportsBySerial(serial));
    }
}
