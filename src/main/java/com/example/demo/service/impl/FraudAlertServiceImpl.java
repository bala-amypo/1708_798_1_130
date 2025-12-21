@Service
public class FraudAlertServiceImpl implements FraudAlertService {

    private final FraudAlertRecordRepository repo;

    public FraudAlertServiceImpl(FraudAlertRecordRepository repo) {
        this.repo = repo;
    }

    public FraudAlertRecord createAlert(FraudAlertRecord alert) {
        return repo.save(alert);
    }

    public FraudAlertRecord resolveAlert(Long id) {
        FraudAlertRecord alert = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Request not found"));
        alert.setResolved(true);
        return repo.save(alert);
    }
}
