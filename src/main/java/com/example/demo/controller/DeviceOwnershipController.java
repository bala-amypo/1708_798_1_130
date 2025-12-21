package com.example.demo.controller;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.service.DeviceOwnershipService;
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
@RequestMapping("/api/devices")
@Tag(name = "Device Ownership", description = "Device management endpoints")
@SecurityRequirement(name = "bearerAuth")
public class DeviceOwnershipController {
    
    private final DeviceOwnershipService deviceService;
    
    public DeviceOwnershipController(DeviceOwnershipService deviceService) {
        this.deviceService = deviceService;
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Register a new device")
    public ResponseEntity<?> registerDevice(@RequestBody DeviceOwnershipRecord device) {
        try {
            DeviceOwnershipRecord savedDevice = deviceService.registerDevice(device);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDevice);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    @Operation(summary = "Get all devices")
    public ResponseEntity<List<DeviceOwnershipRecord>> getAllDevices() {
        List<DeviceOwnershipRecord> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get device by ID")
    public ResponseEntity<?> getDeviceById(@PathVariable Long id) {
        try {
            return deviceService.getBySerial(id.toString())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/serial/{serialNumber}")
    @Operation(summary = "Get device by serial number")
    public ResponseEntity<?> getDeviceBySerial(@PathVariable String serialNumber) {
        return deviceService.getBySerial(serialNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update device status")
    public ResponseEntity<?> updateDeviceStatus(@PathVariable Long id, 
                                                @RequestParam boolean active) {
        try {
            DeviceOwnershipRecord updated = deviceService.updateDeviceStatus(id, active);
            return ResponseEntity.ok(updated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}