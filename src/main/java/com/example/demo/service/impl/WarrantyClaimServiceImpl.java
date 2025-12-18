package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.WarrantyClaimService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

@Service
public class WarrantyClaimServiceImpl implements WarrantyClaimService {

    private final WarrantyClaimRecordRepository claimRepository;
    private final DeviceOwnershipRecordRepository deviceRepository;
    private final StolenDeviceReportRepository stolenRepository;
    private final FraudAlertRecordRepository fraudAlertRepository;
    private final FraudRuleRepository fraudRuleRepository;

    // ⚠️ DO NOT CHANGE ORDER — REQUIRED BY TEST SUITE
    public WarrantyClaimServiceImpl(
            WarrantyClaimRecordRepository claimRepository,
            DeviceOwnershipRecordRepository deviceRepository,
            StolenDeviceReportRepository stolenRepository,
            FraudAlertRecordRepository fraudAlertRepository,
            FraudRuleRepository fraudRuleRepository) {

        this.claimRepository = claimRepository;
        this.deviceRepository = deviceRepository;
        this.stolenRepository = stolenRepository;
        this.fraudAlertRepository = fraudAlertRepository;
        this.fraudRuleRepository = fraudRuleRepository;
    }

    @Override
    public WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim) {

        // 1️⃣ Check if device exists
        DeviceOwnershipRecord device = deviceRepository
                .findBySerialNumber(claim.getSerialNumber())
                .orElseThrow(() -> new NoSuchElementException("Device not found"));

        boolean flagged = false;

        // 2️⃣ Check if device is stolen
        if (stolenRepository.existsBySerialNumber(claim.getSerialNumber())) {
            createFraudAlert(claim, "STOLEN_DEVICE", "HIGH",
                    "Claim submitted for stolen device");
            flagged = true;
        }

        // 3️⃣ Check if warranty expired
        if (device.getWarrantyExpiration() != null &&
                device.getWarrantyExpiration().isBefore(LocalDate.now())) {
            createFraudAlert(claim, "WARRANTY_EXPIRED", "MEDIUM",
                    "Warranty expired for device");
            flagged = true;
        }

        // 4️⃣ Check duplicate claim (same serial + reason)
        if (claimRepository.existsBySerialNumberAndClaimReason(
                claim.getSerialNumber(), claim.getClaimReason())) {
            createFraudAlert(claim, "DUPLICATE_CLAIM", "LOW",
                    "Duplicate claim for same device and reason");
            flagged = true;
        }

        // 5️⃣ Update status if flagged
        if (flagged) {
            claim.setStatus("FLAGGED");
        }

        // 6️⃣ Save claim
        return claimRepository.save(claim);
    }

    private void createFraudAlert(
            WarrantyClaimRecord claim,
            String alertType,
            String severity,
            String message) {

        FraudAlertRecord alert = new FraudAlertRecord();
        alert.setClaimId(claim.getId());
        alert.setSerialNumber(claim.getSerialNumber());
        alert.setAlertType(alertType);
        alert.setSeverity(severity);
        alert.setMessage(message);
        alert.setAlertDate(LocalDateTime.now());
        alert.setResolved(false);

        fraudAlertRepository.save(alert);
    }

    @Override
    public WarrantyClaimRecord updateClaimStatus(Long claimId, String status) {
        WarrantyClaimRecord claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new NoSuchElementException("Claim not found"));
        claim.setStatus(status);
        return claimRepository.save(claim);
    }

    @Override
    public WarrantyClaimRecord getClaimById(Long id) {
        return claimRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Claim not found"));
    }

    @Override
    public List<WarrantyClaimRecord> getClaimsBySerial(String serialNumber) {
        return claimRepository.findBySerialNumber(serialNumber);
    }

    @Override
    public List<WarrantyClaimRecord> getAllClaims() {
        return claimRepository.findAll();
    }
}
