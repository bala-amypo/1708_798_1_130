package com.example.demo.service.impl;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.repository.*;
import com.example.demo.service.StolenDeviceService;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

@Service
public class StolenDeviceServiceImpl implements StolenDeviceService {

    private final StolenDeviceReportRepository stolenRepo;
    private final DeviceOwnershipRecordRepository deviceRepo;

    public StolenDeviceServiceImpl(
            StolenDeviceReportRepository stolenRepo,
            DeviceOwnershipRecordRepository deviceRepo) {
        this.stolenRepo = stolenRepo;
        this.deviceRepo = deviceRepo;
    }

    public StolenDeviceReport reportStolen(StolenDeviceReport report) {
        deviceRepo.findBySerialNumber(report.getSerialNumber())
                .orElseThrow(() -> new NoSuchElementException("Device not found"));
        return stolenRepo.save(report);
    }

    public List<StolenDeviceReport> getReportsBySerial(String serial) {
        return stolenRepo.findBySerialNumber(serial);
    }

    public StolenDeviceReport getReportById(Long id) {
        return stolenRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Report not found"));
    }

    public List<StolenDeviceReport> getAllReports() {
        return stolenRepo.findAll();
    }
}
