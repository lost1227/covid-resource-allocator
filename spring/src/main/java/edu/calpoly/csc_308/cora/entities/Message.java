package edu.calpoly.csc_308.cora.entities;

import lombok.Data;

@Data
public class Message {
    public final Long id;
    public final Long sender;
    public final Long receiver;
    
    public final String messageText;

    public final Long sentTs;

    public Message(Long id, Long sender, Long receiver, String messageText, Long sentTs) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.messageText = messageText;
        this.sentTs = sentTs;
    }
}
