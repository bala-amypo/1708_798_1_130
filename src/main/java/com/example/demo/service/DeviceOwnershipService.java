package com.example.demo.service;

import com.example.demo.model.DeviceOwnershipRecord;
import java.util.List;

public interface DeviceOwnershipService {

    DeviceOwnershipRecord register(DeviceOwnershipRecord record);

    DeviceOwnershipRecord getBySerial(String serial);

    List<DeviceOwnershipRecord> getAll();

    DeviceOwnershipRecord updateStatus(Long id, boolean active);
}
