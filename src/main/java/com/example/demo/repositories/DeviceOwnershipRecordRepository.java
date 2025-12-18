package com.example.demo.repository;

import com.example.demo.entity.DeviceOwnershipRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceOwnershipRecordRepository
        extends JpaRepository<DeviceOwnershipRecord, Long> {

    boolean existsBySerialNumber(String serialNumber);

    Optional<DeviceOwnershipRecord> findBySerialNumber(String serialNumber);
}
