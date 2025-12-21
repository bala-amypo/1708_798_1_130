package com.example.demo.service.impl;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.model.StolenDeviceReport;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.repository.StolenDeviceReportRepository;
import com.example.demo.service.StolenDeviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StolenDeviceServiceImpl implements StolenDeviceService {
    
    private final StolenDeviceReportRepository stolenDeviceRepository;
    private final DeviceOwnershipRecordRepository deviceRepository;
    
    public StolenDeviceServiceImpl(
            StolenDeviceReportRepository stolenDeviceRepository,
            DeviceOwnershipRecordRepository deviceRepository) {
        this.stolenDeviceRepository = stolenDeviceRepository;
        this.deviceRepository = deviceRepository;
    }
    
    @Override
    @Transactional
    public StolenDeviceReport reportStolen(StolenDeviceReport report) {
        // Validate device exists
        DeviceOwnershipRecord device = deviceRepository.findBySerialNumber(report.getSerialNumber())
                .orElseThrow(() -> new NoSuchElementException("Device not found"));
        
        report.setDevice(device);
        return stolenDeviceRepository.save(report);
    }
    
    @Override
    public List<StolenDeviceReport> getReportsBySerial(String serialNumber) {
        return stolenDeviceRepository.findBySerialNumber(serialNumber);
    }
    
    @Override
    public Optional<StolenDeviceReport> getReportById(Long id) {
        return stolenDeviceRepository.findById(id);
    }
    
    @Override
    public List<StolenDeviceReport> getAllReports() {
        return stolenDeviceRepository.findAll();
    }
}