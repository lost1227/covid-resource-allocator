package edu.calpoly.csc_308.cora.data.messages;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class MessageDAO {
    private @Id @GeneratedValue Long id;
    
    private Long sender;
    private Long receiver;
    
    private String messageText;

    private Long sentTs;

    public MessageDAO(Long sender, Long receiver, String messageText, Long sentTs) {
        this.sender = sender;
        this.receiver = receiver;
        this.messageText = messageText;
        this.sentTs = sentTs;
    }
}
