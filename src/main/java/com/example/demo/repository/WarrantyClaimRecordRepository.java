package com.example.demo.repository;

import com.example.demo.model.WarrantyClaimRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WarrantyClaimRecordRepository
        extends JpaRepository<WarrantyClaimRecord, Long> {
    boolean existsBySerialNumberAndClaimReason(String serialNumber, String claimReason);
    List<WarrantyClaimRecord> findBySerialNumber(String serialNumber);
}
