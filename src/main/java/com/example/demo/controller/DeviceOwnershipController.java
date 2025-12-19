package com.example.demo.controller;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.service.DeviceOwnershipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices")
public class DeviceOwnershipController {

    private final DeviceOwnershipService service;

    public DeviceOwnershipController(DeviceOwnershipService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody DeviceOwnershipRecord device) {
        return ResponseEntity.ok(service.registerDevice(device));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAllDevices());
    }

    @GetMapping("/serial/{serial}")
    public ResponseEntity<?> getBySerial(@PathVariable String serial) {
        return ResponseEntity.ok(service.getBySerial(serial));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {
        return ResponseEntity.ok(service.updateDeviceStatus(id, active));
    }
}
