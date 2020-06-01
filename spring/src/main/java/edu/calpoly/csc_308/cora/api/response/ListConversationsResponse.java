package edu.calpoly.csc_308.cora.api.response;

import java.util.List;

import edu.calpoly.csc_308.cora.entities.Message;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ListConversationsResponse extends ResponseModel {

  @Data
  public static class ConversationResponse {
    private final Long userId;
    private final List<Message> messageHistory;
  }

  private final Long userId;
  private final List<ConversationResponse> conversations;
}
