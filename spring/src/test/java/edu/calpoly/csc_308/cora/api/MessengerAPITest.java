package edu.calpoly.csc_308.cora.api;

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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MessengerAPITest {

    @LocalServerPort
    private int port;
    
    @Autowired
    private MessengerAPI api;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private Messenger messengerService;

    @MockBean
    private UserRepository userService;

    @BeforeEach
    public void setup() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        List<Conversation> conversations = new ArrayList<>();
        Mockito.doReturn(conversations).when(messengerService).listConversations(Mockito.any());

        UserDAO dao = new UserDAO(
            new UserDAO.ProfileInfo(
            "Jordan Powers",
            "Long Beach, CA",
            "volunteer",
            "Student living in Long Beach, CA", 
            new String[] { "programming" }),
          -1L,
          "test",
          encoder.encode("password123"));
        dao.setId(0L);
        Mockito.doReturn(dao).when(userService).findByUsername("test");
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(api).isNotNull();
    }


    @Test
    public void testSendMessage() {
        SendMessageRequestModel request = new SendMessageRequestModel();
        request.setReceiverId(1L);
        request.setMessageText("Test Message");
        
        ResponseEntity<SuccessResponse> entity = this.restTemplate.withBasicAuth("test", "password123").postForEntity(
            "http://localhost:"+this.port+"/api/message/post",
            request,
            SuccessResponse.class);
        
        assertThat(entity.getStatusCodeValue()).isEqualTo(200);

        SuccessResponse value = entity.getBody();

        //System.out.println("Interactions" + Mockito.mockingDetails(userService).printInvocations());

        assertThat(value).isNotNull();
        assertThat(value.getOk()).isTrue();
    }

    @Test
    public void testListConversation() {
        ListConversationsResponse response = this.restTemplate.withBasicAuth("test", "password123").getForObject(
            "http://localhost:"+this.port+"/api/message/conversations",
            ListConversationsResponse.class);
        
        assertThat(response).isNotNull();
        assertThat(response.getOk()).isTrue();
    }
}
