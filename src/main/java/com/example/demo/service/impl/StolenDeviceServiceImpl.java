@Service
public class StolenDeviceServiceImpl implements StolenDeviceService {

    private final StolenDeviceReportRepository repo;
    private final DeviceOwnershipRecordRepository deviceRepo;

    public StolenDeviceServiceImpl(
            StolenDeviceReportRepository repo,
            DeviceOwnershipRecordRepository deviceRepo) {
        this.repo = repo;
        this.deviceRepo = deviceRepo;
    }

    public StolenDeviceReport reportStolen(StolenDeviceReport report) {
        deviceRepo.findBySerialNumber(report.getSerialNumber())
                .orElseThrow(() -> new NoSuchElementException("Device not found"));
        return repo.save(report);
    }

    public List<StolenDeviceReport> getAllReports() {
        return repo.findAll();
    }
}
