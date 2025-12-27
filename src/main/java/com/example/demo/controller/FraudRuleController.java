package com.example.demo.controller;

import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fraud-rules")
@Tag(name = "Rule", description = "Fraud rule management")
public class FraudRuleController {
    
    private final FraudRuleService ruleService;
    
    public FraudRuleController(FraudRuleService ruleService) {
        this.ruleService = ruleService;
    }
    
    @PostMapping
    public ResponseEntity<FraudRule> createRule(@RequestBody FraudRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }
    
    @GetMapping
    public ResponseEntity<List<FraudRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FraudRule> getRuleById(@PathVariable Long id) {
        // Implementation would need to be added to service
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<FraudRule>> getActiveRules() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<FraudRule> updateRule(@PathVariable Long id, @RequestBody FraudRule rule) {
        return ResponseEntity.ok(ruleService.updateRule(id, rule));
    }
}