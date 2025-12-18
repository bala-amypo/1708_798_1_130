package com.example.demo.service.impl;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.service.DeviceOwnershipService;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

@Service
public class DeviceOwnershipServiceImpl implements DeviceOwnershipService {

    private final DeviceOwnershipRecordRepository repo;

    public DeviceOwnershipServiceImpl(DeviceOwnershipRecordRepository repo) {
        this.repo = repo;
    }

    public DeviceOwnershipRecord registerDevice(DeviceOwnershipRecord device) {
        if (repo.existsBySerialNumber(device.getSerialNumber())) {
            throw new IllegalArgumentException("Duplicate serial number");
        }
        return repo.save(device);
    }

    public DeviceOwnershipRecord getBySerial(String serial) {
        return repo.findBySerialNumber(serial)
                .orElseThrow(() -> new NoSuchElementException("Device not found"));
    }

    public List<DeviceOwnershipRecord> getAllDevices() {
        return repo.findAll();
    }

    public DeviceOwnershipRecord updateDeviceStatus(Long id, boolean active) {
        DeviceOwnershipRecord d = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Device not found"));
        d.setActive(active);
        return repo.save(d);
    }
}
