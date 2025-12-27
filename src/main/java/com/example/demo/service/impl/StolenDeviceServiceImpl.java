package com.example.demo.service.impl;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.repository.StolenDeviceReportRepository;
import com.example.demo.service.StolenDeviceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StolenDeviceServiceImpl implements StolenDeviceService {
    
    private final StolenDeviceReportRepository stolenRepo;
    private final DeviceOwnershipRecordRepository deviceRepo;
    
    public StolenDeviceServiceImpl(StolenDeviceReportRepository stolenRepo,
                                   DeviceOwnershipRecordRepository deviceRepo) {
        this.stolenRepo = stolenRepo;
        this.deviceRepo = deviceRepo;
    }
    
    @Override
    public StolenDeviceReport reportStolen(StolenDeviceReport report) {
        deviceRepo.findBySerialNumber(report.getSerialNumber())
            .orElseThrow(() -> new NoSuchElementException("Device not found"));
        return stolenRepo.save(report);
    }
    
    @Override
    public List<StolenDeviceReport> getReportsBySerial(String serialNumber) {
        return stolenRepo.findBySerialNumber(serialNumber);
    }
    
    @Override
    public Optional<StolenDeviceReport> getReportById(Long id) {
        return stolenRepo.findById(id);
    }
    
    @Override
    public List<StolenDeviceReport> getAllReports() {
        return stolenRepo.findAll();
    }
}