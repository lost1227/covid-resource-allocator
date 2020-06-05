package edu.calpoly.csc_308.cora.api.finian;
import edu.calpoly.csc_308.cora.api.LoginAPI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import org.hamcrest.collection.IsEmptyCollection;
import static org.hamcrest.CoreMatchers.*;

import java.util.Collection;
import java.util.List;

import edu.calpoly.csc_308.cora.data.users.UserDAO;
import edu.calpoly.csc_308.cora.data.users.UserRepository;
import edu.calpoly.csc_308.cora.entities.User;
import edu.calpoly.csc_308.cora.services.UserManager;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.calpoly.csc_308.cora.api.MessengerAPI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.ResponseEntity;

import org.mockito.Mockito;

import edu.calpoly.csc_308.cora.api.request.SendMessageRequestModel;
import edu.calpoly.csc_308.cora.api.response.ListConversationsResponse;
import edu.calpoly.csc_308.cora.api.response.SuccessResponse;
import edu.calpoly.csc_308.cora.data.users.UserDAO;
import edu.calpoly.csc_308.cora.data.users.UserRepository;
import edu.calpoly.csc_308.cora.entities.Conversation;
import edu.calpoly.csc_308.cora.services.Messenger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.security.core.Authentication;

import edu.calpoly.csc_308.cora.api.request.EditUserRequestModel;
import edu.calpoly.csc_308.cora.api.request.NewUserRequestModel;
import edu.calpoly.csc_308.cora.api.response.ResponseModel;
import edu.calpoly.csc_308.cora.api.response.SuccessResponse;
import edu.calpoly.csc_308.cora.api.response.UserInfoResponse;
import edu.calpoly.csc_308.cora.entities.User;
import edu.calpoly.csc_308.cora.security.AuthUser;
import edu.calpoly.csc_308.cora.security.AuthUserService;

import org.springframework.beans.factory.annotation.Autowired;

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
        assertThat(value.getOk()).isTrue();
        assertThat(value.getId()).isEqualTo(0L);
    }


}
