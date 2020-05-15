package edu.calpoly.csc_308.cora.api;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import edu.calpoly.csc_308.cora.api.response.UserInfoResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserInfoAPITest {

    @LocalServerPort
    private int port;
    
    @Autowired
    private MessengerAPI api;

    @Autowired
    private TestRestTemplate restTemplate;
    
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
