package com.example.demo.service.impl;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.model.FraudAlertRecord;
import com.example.demo.model.FraudRule;
import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.repository.*;
import com.example.demo.service.WarrantyClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarrantyClaimServiceImpl implements WarrantyClaimService {
    
    private final WarrantyClaimRecordRepository claimRepo;
    private final DeviceOwnershipRecordRepository deviceRepo;
    private final StolenDeviceReportRepository stolenRepo;
    private final FraudAlertRecordRepository alertRepo;
    private final FraudRuleRepository ruleRepo;
    
    @Override
    public WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim) {
        // Check if device exists
        DeviceOwnershipRecord device = deviceRepo.findBySerialNumber(claim.getSerialNumber())
                .orElseThrow(() -> new NoSuchElementException("Device not found"));
        
        // Initialize status
        claim.setStatus("PENDING");
        
        // Check for flags
        boolean flagged = false;
        
        // Check duplicate claim reason
        if (claimRepo.existsBySerialNumberAndClaimReason(claim.getSerialNumber(), claim.getClaimReason())) {
            flagged = true;
        }
        
        // Check if warranty expired
        if (device.getWarrantyExpiration().isBefore(LocalDate.now())) {
            flagged = true;
        }
        
        // Check if device is stolen
        if (stolenRepo.existsBySerialNumber(claim.getSerialNumber())) {
            flagged = true;
        }
        
        // Set status based on flags
        if (flagged) {
            claim.setStatus("FLAGGED");
            // Create fraud alert
            FraudAlertRecord alert = FraudAlertRecord.builder()
                    .serialNumber(claim.getSerialNumber())
                    .claimId(claim.getId())
                    .alertReason("Claim flagged for review")
                    .build();
            alertRepo.save(alert);
        }
        
        return claimRepo.save(claim);
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