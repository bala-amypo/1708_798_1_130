package com.example.demo.service.impl;

import com.example.demo.model.FraudRule;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.service.FraudRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FraudRuleServiceImpl implements FraudRuleService {
    
    private final FraudRuleRepository ruleRepo;
    
    @Override
    public FraudRule createRule(FraudRule rule) {
        if (ruleRepo.findByRuleCode(rule.getRuleCode()).isPresent()) {
            throw new IllegalArgumentException("Rule code already exists");
        }
        return ruleRepo.save(rule);
    }
    
    @Override
    public List<FraudRule> getAllRules() {
        return ruleRepo.findAll();
    }
    
    @Override
    public List<FraudRule> getActiveRules() {
        return ruleRepo.findByActiveTrue();
    }
    
    @Override
    public Optional<FraudRule> getRuleByCode(String ruleCode) {
        return ruleRepo.findByRuleCode(ruleCode);
    }
}