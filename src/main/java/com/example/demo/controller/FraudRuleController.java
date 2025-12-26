package com.example.demo.controller;

import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fraud-rules")
@RequiredArgsConstructor
public class FraudRuleController {
    
    private final FraudRuleService ruleService;
    
    @PostMapping
    public ResponseEntity<FraudRule> createRule(@RequestBody FraudRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }
    
    @GetMapping
    public ResponseEntity<List<FraudRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<FraudRule>> getActiveRules() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }
    
    @GetMapping("/{ruleCode}")
    public ResponseEntity<FraudRule> getRuleByCode(@PathVariable String ruleCode) {
        return ruleService.getRuleByCode(ruleCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}