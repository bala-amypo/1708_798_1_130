package com.example.demo.repository;

import com.example.demo.model.WarrantyClaimRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarrantyClaimRecordRepository extends JpaRepository<WarrantyClaimRecord, Long> {
    List<WarrantyClaimRecord> findBySerialNumber(String serialNumber);
    boolean existsBySerialNumberAndClaimReason(String serialNumber, String claimReason);
    List<WarrantyClaimRecord> findByStatus(String status);
}