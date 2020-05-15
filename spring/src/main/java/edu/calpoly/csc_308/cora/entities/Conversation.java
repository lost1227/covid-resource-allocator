package edu.calpoly.csc_308.cora.entities;

import java.util.List;

public class Conversation {
    public List<Message> messages;

    public Long user1id;
    public Long user2id;

    public Conversation(Long user1, Long user2, List<Message> messages) {
        this.user1id = user1;
        this.user2id = user2;
        this.messages = messages;
    }
}
