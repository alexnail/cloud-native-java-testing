package demo.user;

import java.security.Principal;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

 public Principal getAuthenticatedUser(Principal principal) {
  return principal == null ? () -> "user" : principal;
 }
}
