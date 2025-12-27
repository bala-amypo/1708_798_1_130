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

    public DeviceOwnershipController(DeviceOwnershipService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseEntity<DeviceOwnershipRecord> register(
            @RequestBody DeviceOwnershipRecord record) {
        return ResponseEntity.ok(deviceService.registerDevice(record));
    }

    @GetMapping("/{serial}")
    public ResponseEntity<?> getBySerial(@PathVariable String serial) {
        return deviceService.getBySerial(serial)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<DeviceOwnershipRecord> getAll() {
        return deviceService.getAllDevices();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DeviceOwnershipRecord> updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {
        return ResponseEntity.ok(deviceService.updateDeviceStatus(id, active));
    }
}






































