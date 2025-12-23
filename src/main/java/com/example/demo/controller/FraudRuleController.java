package com.example.demo.controller;

import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fraud-rules")
@Tag(name = "Rule")
public class FraudRuleController {

    private final FraudRuleService ruleService;

    public FraudRuleController(FraudRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<FraudRule> createRule(@RequestBody FraudRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FraudRule> updateRule(
            @PathVariable Long id,
            @RequestBody FraudRule rule) {
        return ResponseEntity.ok(ruleService.updateRule(id, rule));
    }

    @GetMapping("/active")
    public ResponseEntity<List<FraudRule>> getActiveRules() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FraudRule> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ruleService.getRuleById(id)
                        .orElseThrow(() -> new java.util.NoSuchElementException("Request not found"))
        );
    }

    @GetMapping
    public ResponseEntity<List<FraudRule>> getAll() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}
