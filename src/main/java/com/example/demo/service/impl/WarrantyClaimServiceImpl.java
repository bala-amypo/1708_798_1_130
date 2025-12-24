package com.example.demo.service.impl;

import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.repository.*;

import com.example.demo.service.WarrantyClaimService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WarrantyClaimServiceImpl implements WarrantyClaimService {

    private final WarrantyClaimRecordRepository claimRepo;
    private final DeviceOwnershipRecordRepository deviceRepo;
    private final StolenDeviceReportRepository stolenRepo;
    private final FraudAlertRecordRepository alertRepo;
    private final FraudRuleRepository ruleRepo;

    public WarrantyClaimServiceImpl(
            WarrantyClaimRecordRepository claimRepo,
            DeviceOwnershipRecordRepository deviceRepo,
            StolenDeviceReportRepository stolenRepo,
            FraudAlertRecordRepository alertRepo,
            FraudRuleRepository ruleRepo) {

        this.claimRepo = claimRepo;
        this.deviceRepo = deviceRepo;
        this.stolenRepo = stolenRepo;
        this.alertRepo = alertRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public WarrantyClaimRecord submitClaim(WarrantyClaimRecord record) {

        DeviceOwnershipRecord device = deviceRepo.findBySerialNumber(record.getSerialNumber())
                .orElseThrow(() -> new NoSuchElementException("Device not found"));

        boolean flagged = false;

        if (claimRepo.existsBySerialNumberAndClaimReason(
                record.getSerialNumber(), record.getClaimReason())) {
            flagged = true;
        }

        if (device.getWarrantyExpiration() != null &&
                device.getWarrantyExpiration().isBefore(LocalDate.now())) {
            flagged = true;
        }

        if (stolenRepo.existsBySerialNumber(record.getSerialNumber())) {
            flagged = true;
        }

        record.setStatus(flagged ? "FLAGGED" : "PENDING");
        return claimRepo.save(record);
    }

    @Override
    public WarrantyClaimRecord updateClaimStatus(Long id, String status) {

        WarrantyClaimRecord claim = claimRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Claim not found"));

        claim.setStatus(status);
        return claimRepo.save(claim);
    }

    @Override
    public Optional<WarrantyClaimRecord> getClaimById(Long id) {
        return claimRepo.findById(id);
    }

    @Override
    public List<WarrantyClaimRecord> getAllClaims() {
        return claimRepo.findAll();
    }

    @Override
    public List<WarrantyClaimRecord> getClaimsBySerial(String serialNumber) {
        return claimRepo.findBySerialNumber(serialNumber);
    }
}
