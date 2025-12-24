package com.example.demo.service;

import com.example.demo.model.DeviceOwnershipRecord;
import java.util.List;

public interface DeviceOwnershipService {

    DeviceOwnershipRecord registerDevice(DeviceOwnershipRecord device);

    DeviceOwnershipRecord getBySerial(String serialNumber);

    DeviceOwnershipRecord getById(Long id);

    List<DeviceOwnershipRecord> getAllDevices();

    DeviceOwnershipRecord updateDeviceStatus(Long id, boolean active);
}