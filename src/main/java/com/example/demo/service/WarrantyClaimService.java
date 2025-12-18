package com.example.demo.service;

import com.example.demo.entity.WarrantyClaimRecord;
import java.util.List;

public interface WarrantyClaimService {
    WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim);
    WarrantyClaimRecord updateClaimStatus(Long claimId, String status);
    WarrantyClaimRecord getClaimById(Long id);
    List<WarrantyClaimRecord> getClaimsBySerial(String serialNumber);
    List<WarrantyClaimRecord> getAllClaims();
}
