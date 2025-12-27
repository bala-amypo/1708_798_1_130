package com.example.demo.controller;

import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.service.WarrantyClaimService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/claims")
@Tag(name = "Claim", description = "Warranty claim management")
public class WarrantyClaimController {
    
    private final WarrantyClaimService claimService;
    
    public WarrantyClaimController(WarrantyClaimService claimService) {
        this.claimService = claimService;
    }
    
    @PostMapping
    public ResponseEntity<WarrantyClaimRecord> submitClaim(@RequestBody WarrantyClaimRecord claim) {
        return ResponseEntity.ok(claimService.submitClaim(claim));
    }
    
    @GetMapping
    public ResponseEntity<List<WarrantyClaimRecord>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<WarrantyClaimRecord> getClaimById(@PathVariable Long id) {
        Optional<WarrantyClaimRecord> claim = claimService.getClaimById(id);
        return claim.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<List<WarrantyClaimRecord>> getClaimsBySerial(@PathVariable String serialNumber) {
        return ResponseEntity.ok(claimService.getClaimsBySerial(serialNumber));
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<WarrantyClaimRecord> updateClaimStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(claimService.updateClaimStatus(id, status));
    }
}