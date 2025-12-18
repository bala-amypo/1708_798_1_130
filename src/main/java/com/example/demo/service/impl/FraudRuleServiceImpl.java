// package com.example.demo.service.impl;

// import com.example.demo.entity.FraudRule;
// import com.example.demo.repository.FraudRuleRepository;
// import com.example.demo.service.FraudRuleService;

// import java.util.List;
// import java.util.NoSuchElementException;

// import org.springframework.stereotype.Service;

// @Service
// public class FraudRuleServiceImpl implements FraudRuleService {

//     private final FraudRuleRepository repo;

//     public FraudRuleServiceImpl(FraudRuleRepository repo) {
//         this.repo = repo;
//     }

//     public FraudRule createRule(FraudRule rule) {
//         return repo.save(rule);
//     }

//     public FraudRule updateRule(Long id, FraudRule rule) {
//         FraudRule r = repo.findById(id)
//                 .orElseThrow(() -> new NoSuchElementException("Rule not found"));
//         r.setDescription(rule.getDescription());
//         r.setRuleType(rule.getRuleType());
//         r.setActive(rule.getActive());
//         return repo.save(r);
//     }

//     public List<FraudRule> getActiveRules() {
//         return repo.findByActiveTrue();
//     }

//     public FraudRule getRuleByCode(String code) {
//         return repo.findByRuleCode(code)
//                 .orElseThrow(() -> new NoSuchElementException("Rule not found"));
//     }

//     public List<FraudRule> getAllRules() {
//         return repo.findAll();
//     }
// }
