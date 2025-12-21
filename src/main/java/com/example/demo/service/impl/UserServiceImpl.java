@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwt;

    public UserServiceImpl(UserRepository repo,
                           PasswordEncoder encoder,
                           JwtTokenProvider jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public User registerUser(RegisterRequest req) {
        if (repo.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        User user = new User(
                req.getName(),
                req.getEmail(),
                encoder.encode(req.getPassword()),
                req.getRoles()
        );
        return repo.save(user);
    }

    public User loginUser(LoginRequest req) {
        User user = repo.findByEmail(req.getEmail())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid input");
        }
        return user;
    }
}
