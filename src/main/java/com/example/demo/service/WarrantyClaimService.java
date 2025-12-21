package com.example.demo.service;

import com.example.demo.model.WarrantyClaimRecord;
import java.util.List;

public interface WarrantyClaimService {
    WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim);
    WarrantyClaimRecord updateClaimStatus(Long id, String status);
    WarrantyClaimRecord getClaimById(Long id);
    List<WarrantyClaimRecord> getClaimsBySerial(String serial);
    List<WarrantyClaimRecord> getAllClaims();
}
