package edu.calpoly.csc_308.cora.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.calpoly.csc_308.cora.data.messages.MessageRepository;
import edu.calpoly.csc_308.cora.entities.Conversation;
import edu.calpoly.csc_308.cora.entities.Message;
import edu.calpoly.csc_308.cora.data.messages.MessageDAO;

@Service
public class Messenger {

    private MessageRepository repo;

    public Messenger(MessageRepository repo) {
        this.repo = repo;
    }

    private MessageDAO convertMessageDAO(Message message) {
        return new MessageDAO(message.getSender(), message.getReceiver(), message.getMessageText(), message.getSentTs());
    }

    public void postMessage(Message message) {
        repo.save(convertMessageDAO(message));
    }

    public List<Conversation> listConversations(Long userId) {
        List<MessageDAO> ungrouped = repo.findBySenderOrReceiver(userId);
        Map<Long, List<MessageDAO>> grouped = ungrouped.stream().collect(Collectors.groupingBy(message -> message.getReceiver().equals(userId) ? message.getSender() : message.getReceiver()));
        ArrayList<Conversation> conversations = new ArrayList<>();
        for(Map.Entry<Long, List<MessageDAO>> otherEntry : grouped.entrySet()) {
            Long otherId = otherEntry.getKey();
            List<MessageDAO> daos = otherEntry.getValue();
            List<Message> messages = daos.stream().map(Message::fromDAO).collect(Collectors.toList());
            conversations.add(new Conversation(userId, otherId, messages));
        }
        return conversations;
    }
    
    
}
