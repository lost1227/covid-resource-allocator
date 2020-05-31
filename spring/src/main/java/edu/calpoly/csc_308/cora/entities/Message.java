package edu.calpoly.csc_308.cora.entities;

import edu.calpoly.csc_308.cora.data.messages.MessageDAO;
import lombok.Data;

@Data
public class Message {
  private final Long id;
  private final Long sender;
  private final Long receiver;
  
  private final String messageText;

  private final Long sentTs;

  public static Message fromDAO(MessageDAO dao) {
    return new Message(dao.getId(), dao.getSender(), dao.getReceiver(), dao.getMessageText(), dao.getSentTs());
  }
}
