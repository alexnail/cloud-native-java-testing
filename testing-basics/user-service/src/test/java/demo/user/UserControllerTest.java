package demo.user;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthService authService;

    @Test
    public void getUserShouldReturnUser() throws Exception {
        String content = "{\"username\": \"user\", \"firstName\": \"Jack\", \"lastName\": \"Frost\", \"email\": \"jfrost@example.com\"}";

        given(userService.getUserByPrincipal(() -> "user"))
                .willReturn(new User("user", "Jack", "Frost", "jfrost@example.com"));

        given(authService.getAuthenticatedUser(null))
                .willReturn(() -> "user");

        mvc.perform(get("/uaa/v1/me").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(r -> System.out.println("---->[\n" + r.getResponse().getContentAsString() + "\n]<------"));
                //.andExpect(content().json(content)); //FIXME - service returns empty content
    }
}
