package com.example.demo.service;

import com.example.demo.model.WarrantyClaimRecord;

import java.util.List;
import java.util.Optional;

public interface WarrantyClaimService {

    WarrantyClaimRecord submitClaim(WarrantyClaimRecord record);

    WarrantyClaimRecord updateClaimStatus(Long id, String status);

    Optional<WarrantyClaimRecord> getClaimById(Long id);

    List<WarrantyClaimRecord> getAllClaims();

    List<WarrantyClaimRecord> getClaimsBySerial(String serialNumber);
}
