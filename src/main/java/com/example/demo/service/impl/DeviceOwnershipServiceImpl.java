package com.example.demo.service.impl;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.service.DeviceOwnershipService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DeviceOwnershipServiceImpl implements DeviceOwnershipService {
    
    private final DeviceOwnershipRecordRepository deviceRepository;
    
    public DeviceOwnershipServiceImpl(DeviceOwnershipRecordRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }
    
    @Override
    public DeviceOwnershipRecord registerDevice(DeviceOwnershipRecord device) {
        if (deviceRepository.existsBySerialNumber(device.getSerialNumber())) {
            throw new IllegalArgumentException("Serial number already exists");
        }
        return deviceRepository.save(device);
    }
    
    @Override
    public Optional<DeviceOwnershipRecord> getBySerial(String serialNumber) {
        return deviceRepository.findBySerialNumber(serialNumber);
    }
    
    @Override
    public List<DeviceOwnershipRecord> getAllDevices() {
        return deviceRepository.findAll();
    }
    
    @Override
    public DeviceOwnershipRecord updateDeviceStatus(Long id, boolean active) {
        DeviceOwnershipRecord device = deviceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Device not found"));
        device.setActive(active);
        return deviceRepository.save(device);
    }
}