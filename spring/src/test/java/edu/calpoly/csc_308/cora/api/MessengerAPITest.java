package edu.calpoly.csc_308.cora.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.mockito.Mockito;

import edu.calpoly.csc_308.cora.api.request.AuthenticatedRequestModel;
import edu.calpoly.csc_308.cora.api.request.SendMessageRequestModel;
import edu.calpoly.csc_308.cora.api.response.ListConversationsResponse;
import edu.calpoly.csc_308.cora.api.response.SuccessResponse;
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

    @BeforeEach
    public void setup() {
        List<Conversation> conversations = new ArrayList<>();
        Mockito.doReturn(conversations).when(messengerService).listConversations(Mockito.any());
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(api).isNotNull();
    }


    @Test
    public void testSendMessage() {
        SendMessageRequestModel request = new SendMessageRequestModel();
        request.authToken = "abc123";
        request.receiverId = 1L;
        request.messageText = "Test Message";

        SuccessResponse value = this.restTemplate.postForObject(
            "http://localhost:"+this.port+"/api/message/post",
            request,
            SuccessResponse.class);
        
        assertThat(value).isNotNull();
        assertThat(value.ok).isTrue();
    }

    @Test
    public void testListConversation() {
        AuthenticatedRequestModel request = new AuthenticatedRequestModel();
        request.authToken = "abc123";

        ListConversationsResponse response = this.restTemplate.postForObject(
            "http://localhost:"+this.port+"/api/message/conversations", 
            request, 
            ListConversationsResponse.class);
        
        assertThat(response).isNotNull();
        assertThat(response.ok).isTrue();
    }
}
