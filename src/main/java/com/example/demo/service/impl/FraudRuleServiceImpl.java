package com.example.demo.service.impl;

import com.example.demo.model.FraudRule;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.service.FraudRuleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FraudRuleServiceImpl implements FraudRuleService {

    private final FraudRuleRepository ruleRepo;

    public FraudRuleServiceImpl(FraudRuleRepository ruleRepo) {
        this.ruleRepo = ruleRepo;
    }

    @Override
    public FraudRule createRule(FraudRule rule) {
        if (ruleRepo.findByRuleCode(rule.getRuleCode()).isPresent()) {
            throw new IllegalArgumentException("Rule already exists");
        }
        return ruleRepo.save(rule);
    }

    @Override
    public FraudRule updateRule(Long id, FraudRule updatedRule) {
        FraudRule rule = ruleRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Request not found"));

        rule.setRuleType(updatedRule.getRuleType());
        rule.setDescription(updatedRule.getDescription());
        rule.setActive(updatedRule.getActive());

        return ruleRepo.save(rule);
    }

    @Override
    public List<FraudRule> getActiveRules() {
        return ruleRepo.findByActiveTrue();
    }

    @Override
    public Optional<FraudRule> getRuleByCode(String ruleCode) {
        return ruleRepo.findByRuleCode(ruleCode);
    }

    @Override
    public Optional<FraudRule> getRuleById(Long id) {
        return ruleRepo.findById(id);
    }

    @Override
    public List<FraudRule> getAllRules() {
        return ruleRepo.findAll();
    }
}