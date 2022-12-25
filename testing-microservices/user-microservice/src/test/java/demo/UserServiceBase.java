package demo;

import static org.mockito.BDDMockito.given;

import demo.user.AuthService;
import demo.user.User;
import demo.user.UserController;
import demo.user.UserService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserServiceBase {

    @MockBean
    private UserService userService;

    @MockBean
    private AuthService authService;

    @Before
    public void setup() {

        User actual = new User("user", "Jack", "Frost", "jfrost@example.com");
        actual.setLastModified(12345L);
        actual.setCreatedAt(12345L);
        actual.setId(0L);
        given(this.userService.getUserByPrincipal(() -> "user"))
                .willReturn(actual);

        given(this.authService.getAuthenticatedUser(null))
                .willReturn(() -> "user");

        RestAssuredMockMvc.standaloneSetup(new UserController(userService, authService));
    }

    public void assertThatRejectionReasonIsNull(Object rejectionReason) {
        assert rejectionReason == null;
    }
}