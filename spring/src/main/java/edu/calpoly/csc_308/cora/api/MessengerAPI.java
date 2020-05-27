package edu.calpoly.csc_308.cora.api;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;

import edu.calpoly.csc_308.cora.api.request.SendMessageRequestModel;
import edu.calpoly.csc_308.cora.api.response.SuccessResponse;
import edu.calpoly.csc_308.cora.api.response.ListConversationsResponse.ConversationResponse;
import edu.calpoly.csc_308.cora.entities.Conversation;
import edu.calpoly.csc_308.cora.entities.Message;
import edu.calpoly.csc_308.cora.entities.User;
import edu.calpoly.csc_308.cora.security.AuthUser;
import edu.calpoly.csc_308.cora.api.response.ListConversationsResponse;
import edu.calpoly.csc_308.cora.api.response.ResponseModel;
import edu.calpoly.csc_308.cora.services.Messenger;

@RestController
public class MessengerAPI {

    private Logger logger = LoggerFactory.getLogger(MessengerAPI.class);

    private Messenger mess;

    public MessengerAPI(Messenger mess) {
        this.mess = mess;
    }

    @PostMapping("/api/message/post")
    public ResponseModel postMessage(@RequestBody SendMessageRequestModel messageRequest, Authentication authentication) {
        logger.info("postMessage: {}", messageRequest);

        logger.info("principal: {}", authentication.getPrincipal());

        User principal = ((AuthUser) authentication.getPrincipal()).user;

        Long id = principal.id;
        if(id.equals(messageRequest.receiverId)) {
            throw new IllegalArgumentException("Cannot message self");
        }

        Message message = new Message(-1L, id, messageRequest.receiverId, messageRequest.messageText, System.currentTimeMillis());
        
        mess.postMessage(message);
        return new SuccessResponse();
    }

    @GetMapping("/api/message/conversations")
    public ResponseModel listConversations(Authentication authentication) {
        User principal = ((AuthUser) authentication.getPrincipal()).user;
        Long uid = principal.id;
        
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
