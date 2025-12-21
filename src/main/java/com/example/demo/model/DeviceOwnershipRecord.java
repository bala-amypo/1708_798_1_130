package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "device_ownership_records")
public class DeviceOwnershipRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "serial_number", nullable = false, unique = true)
    private String serialNumber;
    
    @Column(name = "owner_name", nullable = false)
    private String ownerName;
    
    @Column(name = "owner_email")
    private String ownerEmail;
    
    @Column(name = "purchase_date")
    private LocalDate purchaseDate;
    
    @Column(name = "warranty_expiration", nullable = false)
    private LocalDate warrantyExpiration;
    
    @Column(name = "active")
    private Boolean active = true;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<WarrantyClaimRecord> claims = new ArrayList<>();
    
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<StolenDeviceReport> stolenReports = new ArrayList<>();
    
    public DeviceOwnershipRecord() {}
    
    public DeviceOwnershipRecord(String serialNumber, String ownerName, 
                                String ownerEmail, LocalDate warrantyExpiration) {
        this.serialNumber = serialNumber;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.warrantyExpiration = warrantyExpiration;
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    
    public String getOwnerEmail() { return ownerEmail; }
    public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }
    
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    
    public LocalDate getWarrantyExpiration() { return warrantyExpiration; }
    public void setWarrantyExpiration(LocalDate warrantyExpiration) { this.warrantyExpiration = warrantyExpiration; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public List<WarrantyClaimRecord> getClaims() { return claims; }
    public void setClaims(List<WarrantyClaimRecord> claims) { this.claims = claims; }
    
    public List<StolenDeviceReport> getStolenReports() { return stolenReports; }
    public void setStolenReports(List<StolenDeviceReport> stolenReports) { this.stolenReports = stolenReports; }
}