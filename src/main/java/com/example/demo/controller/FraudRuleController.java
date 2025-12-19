package com.example.demo.controller;

import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fraud-rules")
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
    public ResponseEntity<FraudRule> getRule(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getAllRules().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rule not found")));
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
