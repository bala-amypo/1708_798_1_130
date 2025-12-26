package com.example.demo.controller;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.service.DeviceOwnershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceOwnershipController {
    
    private final DeviceOwnershipService deviceService;
    
    @PostMapping
    public ResponseEntity<DeviceOwnershipRecord> registerDevice(@RequestBody DeviceOwnershipRecord device) {
        return ResponseEntity.ok(deviceService.registerDevice(device));
    }
    
    @GetMapping("/{serialNumber}")
    public ResponseEntity<DeviceOwnershipRecord> getDevice(@PathVariable String serialNumber) {
        return deviceService.getBySerial(serialNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<DeviceOwnershipRecord>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<DeviceOwnershipRecord> updateStatus(@PathVariable Long id, 
                                                            @RequestParam Boolean active) {
        return ResponseEntity.ok(deviceService.updateDeviceStatus(id, active));
    }
}