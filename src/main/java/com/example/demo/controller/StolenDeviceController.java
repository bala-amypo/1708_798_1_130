package com.example.demo.controller;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.service.StolenDeviceService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stolen")
public class StolenDeviceController {

    private final StolenDeviceService stolenService;

    public StolenDeviceController(StolenDeviceService stolenService) {
        this.stolenService = stolenService;
    }

    @PostMapping
    public ResponseEntity<StolenDeviceReport> report(
            @RequestBody StolenDeviceReport report) {
        return ResponseEntity.ok(stolenService.reportStolen(report));
    }

    @GetMapping
    public List<StolenDeviceReport> getAll() {
        return stolenService.getAllReports();
    }
}
