package com.example.demo.service.impl;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.service.DeviceOwnershipService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceOwnershipServiceImpl implements DeviceOwnershipService {

    private final DeviceOwnershipRecordRepository deviceRepo;

    // ✅ REQUIRED constructor
    public DeviceOwnershipServiceImpl(DeviceOwnershipRecordRepository deviceRepo) {
        this.deviceRepo = deviceRepo;
    }

    // ================================
    // REGISTER DEVICE
    // ================================
    @Override
    public DeviceOwnershipRecord register(DeviceOwnershipRecord record) {

        // duplicate serial check (test7)
        if (deviceRepo.findBySerialNumber(record.getSerialNumber()).isPresent()) {
            throw new RuntimeException("Duplicate device");
        }

        return deviceRepo.save(record);
    }

    // ================================
    // GET BY SERIAL (test8)
    // ================================
    @Override
    public DeviceOwnershipRecord getBySerial(String serial) {

        // ✅ MUST return null (NOT exception)
        return deviceRepo.findBySerialNumber(serial).orElse(null);
    }

    // ================================
    // GET ALL
    // ================================
    @Override
    public List<DeviceOwnershipRecord> getAll() {
        return deviceRepo.findAll();
    }

    // ================================
    // UPDATE STATUS
    // ================================
    @Override
    public DeviceOwnershipRecord updateStatus(Long id, boolean active) {

        DeviceOwnershipRecord record = deviceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        record.setActive(active);
        return deviceRepo.save(record);
    }
}
