package com.example.demo.repository;

import com.example.demo.model.FraudRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FraudRuleRepository
        extends JpaRepository<FraudRule, Long> {

    Optional<FraudRule> findById(Long id);

    Optional<FraudRule> findByRuleCode(String ruleCode);

    List<FraudRule> findAll();

    List<FraudRule> findByActiveTrue();
}
