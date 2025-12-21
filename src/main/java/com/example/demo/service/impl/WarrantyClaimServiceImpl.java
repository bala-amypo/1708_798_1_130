@Service
public class WarrantyClaimServiceImpl implements WarrantyClaimService {

    private final WarrantyClaimRecordRepository claimRepo;
    private final DeviceOwnershipRecordRepository deviceRepo;
    private final StolenDeviceReportRepository stolenRepo;
    private final FraudAlertRecordRepository alertRepo;
    private final FraudRuleRepository ruleRepo;

    public WarrantyClaimServiceImpl(
            WarrantyClaimRecordRepository claimRepo,
            DeviceOwnershipRecordRepository deviceRepo,
            StolenDeviceReportRepository stolenRepo,
            FraudAlertRecordRepository alertRepo,
            FraudRuleRepository ruleRepo) {
        this.claimRepo = claimRepo;
        this.deviceRepo = deviceRepo;
        this.stolenRepo = stolenRepo;
        this.alertRepo = alertRepo;
        this.ruleRepo = ruleRepo;
    }

    public WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim) {

        DeviceOwnershipRecord device = deviceRepo
                .findBySerialNumber(claim.getSerialNumber())
                .orElseThrow(() -> new NoSuchElementException("Offer not found"));

        boolean flagged = false;

        if (stolenRepo.existsBySerialNumber(claim.getSerialNumber())) {
            flagged = true;
        }

        if (device.getWarrantyExpiration().isBefore(LocalDate.now())) {
            flagged = true;
        }

        if (claimRepo.existsBySerialNumberAndClaimReason(
                claim.getSerialNumber(), claim.getClaimReason())) {
            flagged = true;
        }

        if (flagged) {
            claim.setStatus("FLAGGED");
        }

        return claimRepo.save(claim);
    }
}
