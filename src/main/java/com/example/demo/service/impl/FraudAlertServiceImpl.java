package com.example.demo.service.impl;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.repository.FraudAlertRecordRepository;
import com.example.demo.service.FraudAlertService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FraudAlertServiceImpl implements FraudAlertService {

    private final FraudAlertRecordRepository alertRepo;

    public FraudAlertServiceImpl(FraudAlertRecordRepository alertRepo) {
        this.alertRepo = alertRepo;
    }

    @Override
    public FraudAlertRecord createAlert(FraudAlertRecord alert) {
        return alertRepo.save(alert);
    }

    @Override
    public FraudAlertRecord resolveAlert(Long id) {
        FraudAlertRecord alert = alertRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Request not found"));
        alert.setResolved(true);
        return alertRepo.save(alert);
    }

    @Override
    public List<FraudAlertRecord> getAlertsBySerial(String serialNumber) {
        return alertRepo.findBySerialNumber(serialNumber);
    }

    @Override
    public List<FraudAlertRecord> getAlertsByClaim(Long claimId) {
        return alertRepo.findByClaimId(claimId);
    }

    @Override
    public List<FraudAlertRecord> getAllAlerts() {
        return alertRepo.findAll();
    }
}
