package com.example.demo.controller;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.service.DeviceOwnershipService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
@Tag(name = "Device", description = "Device ownership management")
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
    public ResponseEntity<DeviceOwnershipRecord> getDeviceById(@PathVariable Long id) {
        // Implementation would need to be added to service
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<DeviceOwnershipRecord> getDeviceBySerial(@PathVariable String serialNumber) {
        Optional<DeviceOwnershipRecord> device = deviceService.getBySerial(serialNumber);
        return device.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<DeviceOwnershipRecord> updateDeviceStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(deviceService.updateDeviceStatus(id, active));
    }
}