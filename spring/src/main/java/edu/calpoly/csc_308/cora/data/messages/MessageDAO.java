package edu.calpoly.csc_308.cora.data.messages;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MessageDAO {
    public @Id @GeneratedValue Long id;
    
    public Long sender;
    public Long receiver;
    
    public String messageText;

    public Long sentTs;
}
