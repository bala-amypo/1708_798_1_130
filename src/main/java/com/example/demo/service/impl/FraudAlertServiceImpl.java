// package com.example.demo.service.impl;

// import com.example.demo.entity.FraudAlertRecord;
// import com.example.demo.repository.FraudAlertRecordRepository;
// import com.example.demo.service.FraudAlertService;

// import java.util.List;
// import java.util.NoSuchElementException;

// import org.springframework.stereotype.Service;

// @Service
// public class FraudAlertServiceImpl implements FraudAlertService {

//     private final FraudAlertRecordRepository repo;

//     public FraudAlertServiceImpl(FraudAlertRecordRepository repo) {
//         this.repo = repo;
//     }

//     public FraudAlertRecord createAlert(FraudAlertRecord alert) {
//         return repo.save(alert);
//     }

//     public FraudAlertRecord resolveAlert(Long id) {
//         FraudAlertRecord a = repo.findById(id)
//                 .orElseThrow(() -> new NoSuchElementException("Alert not found"));
//         a.setResolved(true);
//         return repo.save(a);
//     }

//     public List<FraudAlertRecord> getAlertsBySerial(String serial) {
//         return repo.findAll().stream()
//                 .filter(a -> a.getSerialNumber().equals(serial))
//                 .toList();
//     }

//     public List<FraudAlertRecord> getAlertsByClaim(Long claimId) {
//         return repo.findByClaimId(claimId);
//     }

//     public List<FraudAlertRecord> getAllAlerts() {
//         return repo.findAll();
//     }
// }
