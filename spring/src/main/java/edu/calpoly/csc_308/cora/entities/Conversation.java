package edu.calpoly.csc_308.cora.entities;

import java.util.Collections;
import java.util.List;

import lombok.Data;

@Data
public class Conversation {
    private final List<Message> messages;

    private final Long user1id;
    private final Long user2id;

    public Conversation(Long user1, Long user2, List<Message> messages) {
        this.user1id = user1;
        this.user2id = user2;
        this.messages = Collections.unmodifiableList(messages);
    }
}
