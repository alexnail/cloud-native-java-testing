package demo.user;

import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByPrincipal(Principal principal) {
        // <1>
        User user = Optional.ofNullable(principal)
                .map(p -> userRepository.findUserByUsername(p.getName()))
                .orElse(null);
        return user;
    }
}
