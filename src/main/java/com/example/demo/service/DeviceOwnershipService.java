package com.example.demo.service;

import com.example.demo.entity.DeviceOwnershipRecord;
import java.util.List;

public interface DeviceOwnershipService {
    DeviceOwnershipRecord registerDevice(DeviceOwnershipRecord device);
    DeviceOwnershipRecord getBySerial(String serialNumber);
    List<DeviceOwnershipRecord> getAllDevices();
    DeviceOwnershipRecord updateDeviceStatus(Long id, boolean active);
}
