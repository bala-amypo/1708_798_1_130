package com.example.demo.service;

import com.example.demo.model.DeviceOwnershipRecord;

import java.util.List;
import java.util.Optional;

public interface DeviceOwnershipService {

    DeviceOwnershipRecord registerDevice(DeviceOwnershipRecord record);

    Optional<DeviceOwnershipRecord> getBySerial(String serialNumber);

    DeviceOwnershipRecord updateDeviceStatus(Long id, boolean active);

    List<DeviceOwnershipRecord> getAllDevices();
}
