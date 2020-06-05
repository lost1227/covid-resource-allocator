package edu.calpoly.csc_308.cora.finian;

import edu.calpoly.csc_308.cora.api.LoginAPI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;

import edu.calpoly.csc_308.cora.data.users.UserDAO;
import edu.calpoly.csc_308.cora.data.users.UserRepository;
import edu.calpoly.csc_308.cora.entities.User;
import edu.calpoly.csc_308.cora.services.UserManager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.mockito.Mockito;

import edu.calpoly.csc_308.cora.api.response.UserInfoResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoginAPITest {
    @LocalServerPort
    private int port;

    @Autowired
    private LoginAPI api;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UserManager userManager;

    @MockBean
    private UserRepository userService;

    @BeforeEach
    public void setup() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //List<Conversation> conversations = new ArrayList<>();
        //Mockito.doReturn(conversations).when(messengerService).listConversations(Mockito.any());

        UserDAO dao = new UserDAO(
                new UserDAO.ProfileInfo(
                        "Testy",
                        "Test, CA",
                        "tester",
                        "tester living in Long Beach, CA",
                        new String[] { "testing" }),
                -1L,
                "test",
                encoder.encode("test123"));
        dao.setId(0L);
        Mockito.doReturn(dao).when(userService).findByUsername("test");

        Mockito.doReturn(new User(0L, "test user", "somewhere", "sometype", "description", new String[]{}, -1L))
                .when(userManager).getUser(0L);
    }

    @Test
    void contextLoads() throws Exception {
        assertThat(api).isNotNull();
    }

    @Test
    void testGetSomeInfo(){
        UserInfoResponse value = this.restTemplate.getForObject(
                "http://localhost:"+this.port+"/api/user/info?id={id}",
                UserInfoResponse.class, 0);
        assertThat(value).isNotNull();
        assertThat(value.isOk()).isTrue();
        assertThat(value.getId()).isEqualTo(0L);
    }


}
