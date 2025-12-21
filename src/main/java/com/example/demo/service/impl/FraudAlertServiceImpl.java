package com.example.demo.service.impl;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.repository.FraudAlertRecordRepository;
import com.example.demo.service.FraudAlertService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FraudAlertServiceImpl implements FraudAlertService {
    
    private final FraudAlertRecordRepository alertRepository;
    
    public FraudAlertServiceImpl(FraudAlertRecordRepository alertRepository) {
        this.alertRepository = alertRepository;
    }
    
    @Override
    public FraudAlertRecord createAlert(FraudAlertRecord alert) {
        return alertRepository.save(alert);
    }
    
    @Override
    public FraudAlertRecord resolveAlert(Long id) {
        FraudAlertRecord alert = alertRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Alert not found"));
        
        alert.setResolved(true);
        return alertRepository.save(alert);
    }
    
    @Override
    public List<FraudAlertRecord> getAlertsBySerial(String serialNumber) {
        return alertRepository.findBySerialNumber(serialNumber);
    }
    
    @Override
    public List<FraudAlertRecord> getAlertsByClaim(Long claimId) {
        return alertRepository.findByClaimId(claimId);
    }
    
    @Override
    public List<FraudAlertRecord> getAllAlerts() {
        return alertRepository.findAll();
    }
}