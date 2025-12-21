package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.WarrantyClaimService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WarrantyClaimServiceImpl implements WarrantyClaimService {
    
    private final WarrantyClaimRecordRepository claimRepository;
    private final DeviceOwnershipRecordRepository deviceRepository;
    private final StolenDeviceReportRepository stolenDeviceRepository;
    private final FraudAlertRecordRepository alertRepository;
    private final FraudRuleRepository ruleRepository;
    
    public WarrantyClaimServiceImpl(
            WarrantyClaimRecordRepository claimRepository,
            DeviceOwnershipRecordRepository deviceRepository,
            StolenDeviceReportRepository stolenDeviceRepository,
            FraudAlertRecordRepository alertRepository,
            FraudRuleRepository ruleRepository) {
        this.claimRepository = claimRepository;
        this.deviceRepository = deviceRepository;
        this.stolenDeviceRepository = stolenDeviceRepository;
        this.alertRepository = alertRepository;
        this.ruleRepository = ruleRepository;
    }
    
    @Override
    @Transactional
    public WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim) {
        // Check if device exists
        DeviceOwnershipRecord device = deviceRepository.findBySerialNumber(claim.getSerialNumber())
                .orElseThrow(() -> new NoSuchElementException("Device not found"));
        
        claim.setDevice(device);
        
        // Check if device is active
        if (!Boolean.TRUE.equals(device.getActive())) {
            claim.setStatus("FLAGGED");
            createFraudAlert(claim, "DEVICE_INACTIVE", "Device is not active");
        }
        
        // Check if device is stolen
        if (stolenDeviceRepository.existsBySerialNumber(claim.getSerialNumber())) {
            claim.setStatus("FLAGGED");
            createFraudAlert(claim, "DEVICE_STOLEN", "Device has been reported as stolen");
        }
        
        // Check if warranty is expired
        if (device.getWarrantyExpiration().isBefore(LocalDate.now())) {
            claim.setStatus("FLAGGED");
            createFraudAlert(claim, "WARRANTY_EXPIRED", "Warranty has expired");
        }
        
        // Check for duplicate claims
        if (claimRepository.existsBySerialNumberAndClaimReason(
                claim.getSerialNumber(), claim.getClaimReason())) {
            claim.setStatus("FLAGGED");
            createFraudAlert(claim, "DUPLICATE_CLAIM", "Duplicate claim detected");
        }
        
        // Apply active fraud rules
        applyFraudRules(claim);
        
        return claimRepository.save(claim);
    }
    
    private void createFraudAlert(WarrantyClaimRecord claim, String alertType, String message) {
        FraudAlertRecord alert = new FraudAlertRecord();
        alert.setClaim(claim);
        alert.setAlertType(alertType);
        alert.setSeverity("HIGH");
        alert.setMessage(message);
        alertRepository.save(alert);
    }
    
    private void applyFraudRules(WarrantyClaimRecord claim) {
        List<FraudRule> activeRules = ruleRepository.findByActiveTrue();
        
        for (FraudRule rule : activeRules) {
            // Implement specific rule logic based on rule code
            switch (rule.getRuleCode()) {
                case "FREQUENT_CLAIMS":
                    checkFrequentClaims(claim);
                    break;
                case "SUSPICIOUS_TIMING":
                    checkSuspiciousTiming(claim);
                    break;
                // Add more rule cases as needed
            }
        }
    }
    
    private void checkFrequentClaims(WarrantyClaimRecord claim) {
        List<WarrantyClaimRecord> existingClaims = claimRepository.findBySerialNumber(claim.getSerialNumber());
        if (existingClaims.size() > 2) { // More than 2 claims for same device
            claim.setStatus("FLAGGED");
            createFraudAlert(claim, "FREQUENT_CLAIMS", "Device has multiple claims");
        }
    }
    
    private void checkSuspiciousTiming(WarrantyClaimRecord claim) {
        DeviceOwnershipRecord device = deviceRepository.findBySerialNumber(claim.getSerialNumber())
                .orElse(null);
        if (device != null && device.getPurchaseDate() != null) {
            LocalDate purchaseDate = device.getPurchaseDate();
            if (purchaseDate.plusDays(7).isAfter(LocalDate.now())) {
                // Claim within 7 days of purchase
                claim.setStatus("FLAGGED");
                createFraudAlert(claim, "SUSPICIOUS_TIMING", "Claim submitted too soon after purchase");
            }
        }
    }
    
    @Override
    public WarrantyClaimRecord updateClaimStatus(Long claimId, String status) {
        WarrantyClaimRecord claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new NoSuchElementException("Claim not found"));
        
        claim.setStatus(status);
        return claimRepository.save(claim);
    }
    
    @Override
    public Optional<WarrantyClaimRecord> getClaimById(Long id) {
        return claimRepository.findById(id);
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