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

    private final FraudRuleService service;

    public FraudRuleController(FraudRuleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FraudRule> create(@RequestBody FraudRule rule) {
        return ResponseEntity.ok(service.createRule(rule));
    }

    @GetMapping
    public ResponseEntity<List<FraudRule>> getAll() {
        return ResponseEntity.ok(service.getAllRules());
    }

    @GetMapping("/active")
    public ResponseEntity<List<FraudRule>> getActive() {
        return ResponseEntity.ok(service.getActiveRules());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FraudRule> update(
            @PathVariable Long id,
            @RequestBody FraudRule rule) {
        return ResponseEntity.ok(service.updateRule(id, rule));
    }
}
