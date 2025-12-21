@Service
public class FraudRuleServiceImpl implements FraudRuleService {

    private final FraudRuleRepository repo;

    public FraudRuleServiceImpl(FraudRuleRepository repo) {
        this.repo = repo;
    }

    public FraudRule createRule(FraudRule rule) {
        if (repo.findByRuleCode(rule.getRuleCode()).isPresent()) {
            throw new IllegalArgumentException("Rule already exists");
        }
        return repo.save(rule);
    }

    public List<FraudRule> getActiveRules() {
        return repo.findByActiveTrue();
    }
}
