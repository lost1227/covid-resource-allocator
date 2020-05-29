package edu.calpoly.csc_308.cora.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.mockito.Mockito;

import edu.calpoly.csc_308.cora.api.response.UserInfoResponse;
import edu.calpoly.csc_308.cora.entities.User;
import edu.calpoly.csc_308.cora.services.UserManager;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserInfoAPITest {

    @LocalServerPort
    private int port;
    
    @Autowired
    private MessengerAPI api;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UserManager userManagerService;

    @BeforeEach
    public void setup() {
        Mockito.doReturn(new User(0L, "test user", "somewhere", "sometype", "description", new String[]{}, -1L))
                .when(userManagerService).getUser(0L);
    }
    
    @Test
    public void contextLoads() throws Exception {
        assertThat(api).isNotNull();
    }

    @Test
    public void testShouldGetUserInfo() {

        UserInfoResponse value = this.restTemplate.getForObject(
            "http://localhost:"+this.port+"/api/user/info?id={id}",
            UserInfoResponse.class, 0);
        
        assertThat(value).isNotNull();
        assertThat(value.ok).isTrue();
        assertThat(value.id).isEqualTo(0L);
    }
}
