package com.example.demo.controller;

import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.service.WarrantyClaimService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/claims")
@Tag(name = "Warranty Claims", description = "Warranty claim management endpoints")
@SecurityRequirement(name = "bearerAuth")
public class WarrantyClaimController {
    
    private final WarrantyClaimService claimService;
    
    public WarrantyClaimController(WarrantyClaimService claimService) {
        this.claimService = claimService;
    }
    
    @PostMapping
    @Operation(summary = "Submit a warranty claim")
    public ResponseEntity<?> submitClaim(@RequestBody WarrantyClaimRecord claim) {
        try {
            WarrantyClaimRecord savedClaim = claimService.submitClaim(claim);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedClaim);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    @Operation(summary = "Get all claims")
    public ResponseEntity<List<WarrantyClaimRecord>> getAllClaims() {
        List<WarrantyClaimRecord> claims = claimService.getAllClaims();
        return ResponseEntity.ok(claims);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get claim by ID")
    public ResponseEntity<?> getClaimById(@PathVariable Long id) {
        return claimService.getClaimById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/serial/{serialNumber}")
    @Operation(summary = "Get claims by serial number")
    public ResponseEntity<List<WarrantyClaimRecord>> getClaimsBySerial(@PathVariable String serialNumber) {
        List<WarrantyClaimRecord> claims = claimService.getClaimsBySerial(serialNumber);
        return ResponseEntity.ok(claims);
    }
    
    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN') or hasRole('FRAUDANALYST')")
    @Operation(summary = "Update claim status")
    public ResponseEntity<?> updateClaimStatus(@PathVariable Long id, 
                                               @RequestParam String status) {
        try {
            WarrantyClaimRecord updated = claimService.updateClaimStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}