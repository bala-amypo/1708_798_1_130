@RestController
@RequestMapping("/api/claims")
@Tag(name = "Claim")
public class WarrantyClaimController {

    private final WarrantyClaimService service;

    public WarrantyClaimController(WarrantyClaimService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> submit(@RequestBody WarrantyClaimRecord claim) {
        return ResponseEntity.ok(service.submitClaim(claim));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAllClaims());
    }
}
