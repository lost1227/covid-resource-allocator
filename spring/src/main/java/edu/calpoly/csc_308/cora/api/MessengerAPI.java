package edu.calpoly.csc_308.cora.api;

import java.util.List;
import java.util.stream.Collectors;

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

    private Messenger mess;

    public MessengerAPI(Messenger mess) {
        this.mess = mess;
    }

    @PostMapping("/api/message/post")
    public ResponseModel postMessage(@RequestBody SendMessageRequestModel messageRequest, Authentication authentication) {
      
        User principal = ((AuthUser) authentication.getPrincipal()).user;

        Long id = principal.getId();
        if(id.equals(messageRequest.getReceiverId())) {
            throw new IllegalArgumentException("Cannot message self");
        }

        Message message = new Message(-1L, id, messageRequest.getReceiverId(), messageRequest.getMessageText(), System.currentTimeMillis());
        
        mess.postMessage(message);
        return new SuccessResponse();
    }

    @GetMapping("/api/message/conversations")
    public ResponseModel listConversations(Authentication authentication) {
        User principal = ((AuthUser) authentication.getPrincipal()).user;
        Long uid = principal.getId();

        List<Conversation> conversations = mess.listConversations(uid);
        List<ConversationResponse> responses = conversations.stream().map(convo -> {
            Long otherId = convo.getUser1id().equals(uid) ? convo.getUser2id() : convo.getUser1id();
            return new ConversationResponse(otherId, convo.getMessages());
        }).collect(Collectors.toList());

        return new ListConversationsResponse(uid, responses);
    }
    
}
