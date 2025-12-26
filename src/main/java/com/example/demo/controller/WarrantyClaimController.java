package com.example.demo.controller;

import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.service.WarrantyClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class WarrantyClaimController {
    
    private final WarrantyClaimService claimService;
    
    @PostMapping
    public ResponseEntity<WarrantyClaimRecord> submitClaim(@RequestBody WarrantyClaimRecord claim) {
        return ResponseEntity.ok(claimService.submitClaim(claim));
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<WarrantyClaimRecord> updateStatus(@PathVariable Long id, 
                                                          @RequestParam String status) {
        return ResponseEntity.ok(claimService.updateClaimStatus(id, status));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<WarrantyClaimRecord> getClaim(@PathVariable Long id) {
        return claimService.getClaimById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<WarrantyClaimRecord>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }
    
    @GetMapping("/device/{serialNumber}")
    public ResponseEntity<List<WarrantyClaimRecord>> getClaimsByDevice(@PathVariable String serialNumber) {
        return ResponseEntity.ok(claimService.getClaimsBySerial(serialNumber));
    }
}