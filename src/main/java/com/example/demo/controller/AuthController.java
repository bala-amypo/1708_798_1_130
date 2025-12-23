package com.example.demo.controller;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.service.DeviceOwnershipService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceOwnershipController {

    private final DeviceOwnershipService deviceService;

    // ✅ constructor injection
    public DeviceOwnershipController(DeviceOwnershipService deviceService) {
        this.deviceService = deviceService;
    }

    // ================================
    // REGISTER DEVICE
    // ================================
    @PostMapping
    public ResponseEntity<DeviceOwnershipRecord> register(
            @RequestBody DeviceOwnershipRecord device) {

        return ResponseEntity.ok(deviceService.register(device));
    }

    // ================================
    // UPDATE STATUS
    // ================================
    @PutMapping("/{id}/status")
    public ResponseEntity<DeviceOwnershipRecord> updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        return ResponseEntity.ok(deviceService.updateStatus(id, active));
    }

    // ================================
    // GET BY SERIAL (test8 safe)
    // ================================
    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<DeviceOwnershipRecord> getBySerial(
            @PathVariable String serialNumber) {

        return ResponseEntity.ok(deviceService.getBySerial(serialNumber));
    }

    // ================================
    // GET ALL
    // ================================
    @GetMapping
    public ResponseEntity<List<DeviceOwnershipRecord>> getAll() {
        return ResponseEntity.ok(deviceService.getAll());
    }
}
