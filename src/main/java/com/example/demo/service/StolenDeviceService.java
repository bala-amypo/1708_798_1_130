package com.example.demo.service;

import com.example.demo.model.StolenDeviceReport;
import java.util.List;

public interface StolenDeviceService {
    StolenDeviceReport reportStolen(StolenDeviceReport report);
    List<StolenDeviceReport> getReportsBySerial(String serial);
    StolenDeviceReport getReportById(Long id);
    List<StolenDeviceReport> getAllReports();
}
