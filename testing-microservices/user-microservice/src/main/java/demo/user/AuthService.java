package demo.user;

import java.security.Principal;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

 public Principal getAuthenticatedUser(Principal principal) {
  // Retrieves a dummy user principal
  // for this example project
  return principal == null ? new User() : principal;
 }
}
