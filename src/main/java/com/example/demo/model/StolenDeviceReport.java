package com.example.demo.model;

import java.time.LocalDateTime;

public class StolenDeviceReport {

    private Long id;
    private String serialNumber;
    private LocalDateTime reportedAt = LocalDateTime.now();

    public StolenDeviceReport() {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final StolenDeviceReport r = new StolenDeviceReport();

        public Builder id(Long id) { r.setId(id); return this; }
        public Builder serialNumber(String s) { r.setSerialNumber(s); return this; }

        public StolenDeviceReport build() { return r; }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
}
