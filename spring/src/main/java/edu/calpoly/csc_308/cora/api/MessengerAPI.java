package edu.calpoly.csc_308.cora.api;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.calpoly.csc_308.cora.api.request.AuthenticatedRequestModel;
import edu.calpoly.csc_308.cora.api.request.SendMessageRequestModel;
import edu.calpoly.csc_308.cora.api.response.SuccessResponse;
import edu.calpoly.csc_308.cora.api.response.ListConversationsResponse.ConversationResponse;
import edu.calpoly.csc_308.cora.entities.Conversation;
import edu.calpoly.csc_308.cora.entities.Message;
import edu.calpoly.csc_308.cora.api.response.ListConversationsResponse;
import edu.calpoly.csc_308.cora.api.response.ResponseModel;
import edu.calpoly.csc_308.cora.services.Authenticator;
import edu.calpoly.csc_308.cora.services.Messenger;

@RestController
public class MessengerAPI {

    private Logger logger = LoggerFactory.getLogger(MessengerAPI.class);

    private Authenticator auth;
    private Messenger mess;

    public MessengerAPI(Authenticator auth, Messenger mess) {
        this.auth = auth;
        this.mess = mess;
    }

    @PostMapping("/api/message/post")
    public ResponseModel postMessage(@RequestBody SendMessageRequestModel messageRequest) {
        logger.info("postMessage: {}", messageRequest);

        Long id = this.auth.getUIDforKey(messageRequest.authToken);
        if(id.equals(messageRequest.receiverId)) {
            throw new IllegalArgumentException("Cannot message self");
        }

        Message message = new Message(-1L, this.auth.getUIDforKey(messageRequest.authToken), messageRequest.receiverId, messageRequest.messageText, System.currentTimeMillis());
        
        mess.postMessage(message);
        return new SuccessResponse();
    }

    @PostMapping("/api/message/conversations")
    public ResponseModel listConversations(@RequestBody AuthenticatedRequestModel request) {
        Long uid = auth.getUIDforKey(request.authToken);
        logger.info("ListConversationsFor: {}", uid);
        
        ListConversationsResponse response = new ListConversationsResponse();
        response.userId = uid;

        List<Conversation> conversations = mess.listConversations(uid);
        List<ConversationResponse> responses = conversations.stream().map(convo -> {
            Long otherId = (convo.user1id == uid) ? convo.user2id : convo.user1id;
            ConversationResponse res = new ConversationResponse();
            res.userId = otherId;
            res.messageHistory = convo.messages;
            return res;
        }).collect(Collectors.toList());

        response.conversations = responses;

        return response;
    }
    
}
