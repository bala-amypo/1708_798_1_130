package com.example.demo.controller;

import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;
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
@RequestMapping("/api/fraud-rules")
@Tag(name = "Fraud Rules", description = "Fraud rule management endpoints")
@SecurityRequirement(name = "bearerAuth")
public class FraudRuleController {
    
    private final FraudRuleService fraudRuleService;
    
    public FraudRuleController(FraudRuleService fraudRuleService) {
        this.fraudRuleService = fraudRuleService;
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new fraud rule")
    public ResponseEntity<?> createRule(@RequestBody FraudRule rule) {
        try {
            FraudRule createdRule = fraudRuleService.createRule(rule);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRule);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    @Operation(summary = "Get all fraud rules")
    public ResponseEntity<List<FraudRule>> getAllRules() {
        List<FraudRule> rules = fraudRuleService.getAllRules();
        return ResponseEntity.ok(rules);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get fraud rule by ID")
    public ResponseEntity<?> getRuleById(@PathVariable Long id) {
        try {
            return fraudRuleService.getRuleByCode(id.toString())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/active")
    @Operation(summary = "Get active fraud rules")
    public ResponseEntity<List<FraudRule>> getActiveRules() {
        List<FraudRule> activeRules = fraudRuleService.getActiveRules();
        return ResponseEntity.ok(activeRules);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update fraud rule")
    public ResponseEntity<?> updateRule(@PathVariable Long id, 
                                        @RequestBody FraudRule updatedRule) {
        try {
            FraudRule rule = fraudRuleService.updateRule(id, updatedRule);
            return ResponseEntity.ok(rule);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}