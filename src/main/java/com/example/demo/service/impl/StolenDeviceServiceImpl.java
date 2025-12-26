package com.example.demo.service.impl;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.repository.StolenDeviceReportRepository;
import com.example.demo.service.StolenDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StolenDeviceServiceImpl implements StolenDeviceService {
    
    private final StolenDeviceReportRepository stolenRepo;
    private final DeviceOwnershipRecordRepository deviceRepo;
    
    @Override
    public StolenDeviceReport reportStolen(StolenDeviceReport report) {
        // Check if device exists
        deviceRepo.findBySerialNumber(report.getSerialNumber())
                .orElseThrow(() -> new NoSuchElementException("Device not found"));
        
        // Set report date if not set
        if (report.getReportDate() == null) {
            report.setReportDate(LocalDate.now());
        }
        
        return stolenRepo.save(report);
    }
    
    @Override
    public List<StolenDeviceReport> getAllReports() {
        return stolenRepo.findAll();
    }
    
    @Override
    public List<StolenDeviceReport> getReportsBySerial(String serialNumber) {
        return stolenRepo.findBySerialNumber(serialNumber);
    }
}