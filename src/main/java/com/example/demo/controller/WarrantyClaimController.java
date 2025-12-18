package com.example.demo.controller;
import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.service.WarrantyClaimService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/claims")
@Tag(name = "Warranty Claims")
public class WarrantyClaimController {

    private final WarrantyClaimService warrantyClaimService;

    public WarrantyClaimController(WarrantyClaimService warrantyClaimService) {
        this.warrantyClaimService = warrantyClaimService;
    }

    @PostMapping
    public WarrantyClaimRecord submitClaim(
            @RequestBody WarrantyClaimRecord claim) {
        return warrantyClaimService.submitClaim(claim);
    }

    @PutMapping("/{id}/status")
    public WarrantyClaimRecord updateClaimStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return warrantyClaimService.updateClaimStatus(id, status);
    }

    @GetMapping("/{id}")
    public WarrantyClaimRecord getClaimById(@PathVariable Long id) {
        return warrantyClaimService.getClaimById(id);
    }

    @GetMapping("/serial/{serialNumber}")
    public List<WarrantyClaimRecord> getClaimsBySerial(
            @PathVariable String serialNumber) {
        return warrantyClaimService.getClaimsBySerial(serialNumber);
    }

    @GetMapping
    public List<WarrantyClaimRecord> getAllClaims() {
        return warrantyClaimService.getAllClaims();
    }
}
