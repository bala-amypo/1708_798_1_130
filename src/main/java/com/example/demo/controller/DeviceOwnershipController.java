package com.example.demo.controller;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.service.DeviceOwnershipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceOwnershipController {

    private final DeviceOwnershipService deviceService;

    public DeviceOwnershipController(DeviceOwnershipService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseEntity<DeviceOwnershipRecord> registerDevice(@RequestBody DeviceOwnershipRecord device) {
        return ResponseEntity.ok(deviceService.registerDevice(device));
    }

    @GetMapping
    public ResponseEntity<List<DeviceOwnershipRecord>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceOwnershipRecord> getDevice(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.updateDeviceStatus(id, true));
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<DeviceOwnershipRecord> getBySerial(@PathVariable String serialNumber) {
        return ResponseEntity.ok(deviceService.getBySerial(serialNumber)
                .orElseThrow(() -> new IllegalArgumentException("Device not found")));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DeviceOwnershipRecord> updateStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(deviceService.updateDeviceStatus(id, active));
    }
}
