package com.example.demo.service;

import com.example.demo.model.FraudRule;
import java.util.List;

public interface FraudRuleService {
    FraudRule createRule(FraudRule rule);
    FraudRule updateRule(Long id, FraudRule rule);
    List<FraudRule> getActiveRules();
    FraudRule getRuleByCode(String code);
    List<FraudRule> getAllRules();
}
