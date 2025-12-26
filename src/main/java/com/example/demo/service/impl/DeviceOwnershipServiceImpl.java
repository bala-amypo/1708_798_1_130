package com.example.demo.service.impl;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.service.DeviceOwnershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceOwnershipServiceImpl implements DeviceOwnershipService {
    
    private final DeviceOwnershipRecordRepository deviceRepo;
    
    @Override
    public DeviceOwnershipRecord registerDevice(DeviceOwnershipRecord device) {
        if (deviceRepo.existsBySerialNumber(device.getSerialNumber())) {
            throw new IllegalArgumentException("Device with serial number already exists");
        }
        return deviceRepo.save(device);
    }
    
    @Override
    public Optional<DeviceOwnershipRecord> getBySerial(String serialNumber) {
        return deviceRepo.findBySerialNumber(serialNumber);
    }
    
    @Override
    public List<DeviceOwnershipRecord> getAllDevices() {
        return deviceRepo.findAll();
    }
    
    @Override
    public DeviceOwnershipRecord updateDeviceStatus(Long id, Boolean active) {
        DeviceOwnershipRecord device = deviceRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Device not found"));
        device.setActive(active);
        return deviceRepo.save(device);
    }
}