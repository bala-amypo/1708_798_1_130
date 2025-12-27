package com.example.demo.repository;

import com.example.demo.model.StolenDeviceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StolenDeviceReportRepository extends JpaRepository<StolenDeviceReport, Long> {
    List<StolenDeviceReport> findBySerialNumber(String serialNumber);
    boolean existsBySerialNumber(String serialNumber);
}