package edu.calpoly.csc_308.cora.api.response;

import java.util.List;

import edu.calpoly.csc_308.cora.entities.Message;

public class ListConversationsResponse extends ResponseModel {
    public static class ConversationResponse {
        public Long userId;
        public List<Message> messageHistory;
    }

    public Long userId;
    public List<ConversationResponse> conversations;
}
