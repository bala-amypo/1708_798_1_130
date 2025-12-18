package com.example.demo.repository;

import com.example.demo.entity.FraudAlertRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FraudAlertRecordRepository
        extends JpaRepository<FraudAlertRecord, Long> {

    List<FraudAlertRecord> findByClaimId(Long claimId);
}
