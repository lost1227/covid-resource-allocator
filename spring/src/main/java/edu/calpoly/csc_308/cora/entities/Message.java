package edu.calpoly.csc_308.cora.entities;

import lombok.Data;

@Data
public class Message {
    private final Long id;
    private final Long sender;
    private final Long receiver;
    
    private final String messageText;

    private final Long sentTs;
}
