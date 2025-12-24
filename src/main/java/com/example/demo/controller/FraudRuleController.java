package com.example.demo.controller;

import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
public class FraudRuleController {

    private final FraudRuleService ruleService;

    public FraudRuleController(FraudRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<FraudRule> create(@RequestBody FraudRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @GetMapping("/active")
    public List<FraudRule> getActive() {
        return ruleService.getActiveRules();
    }
}
