package com.example.demo.service;

import com.example.demo.model.*;
import java.util.List;

public interface DeviceOwnershipService {
    DeviceOwnershipRecord registerDevice(DeviceOwnershipRecord device);
    DeviceOwnershipRecord getBySerial(String serial);
    List<DeviceOwnershipRecord> getAllDevices();
    DeviceOwnershipRecord updateDeviceStatus(Long id, boolean active);
}
