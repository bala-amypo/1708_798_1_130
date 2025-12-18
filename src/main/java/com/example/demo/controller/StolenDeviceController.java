package com.example.demo.controller;
import com.example.demo.entity.StolenDeviceReport;
import com.example.demo.service.StolenDeviceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stolen-devices")
@Tag(name = "Stolen Devices")
public class StolenDeviceController {

    private final StolenDeviceService stolenService;

    public StolenDeviceController(StolenDeviceService stolenService) {
        this.stolenService = stolenService;
    }

    @PostMapping
    public StolenDeviceReport reportStolen(
            @RequestBody StolenDeviceReport report) {
        return stolenService.reportStolen(report);
    }

    @GetMapping("/serial/{serialNumber}")
    public List<StolenDeviceReport> getBySerial(
            @PathVariable String serialNumber) {
        return stolenService.getReportsBySerial(serialNumber);
    }

    @GetMapping("/{id}")
    public StolenDeviceReport getById(@PathVariable Long id) {
        return stolenService.getReportById(id);
    }

    @GetMapping
    public List<StolenDeviceReport> getAll() {
        return stolenService.getAllReports();
    }
}
