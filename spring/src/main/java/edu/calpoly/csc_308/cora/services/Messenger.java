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
        MessageDAO dao = new MessageDAO(message.getSender(), message.getReceiver(), message.getMessageText(), message.getSentTs());
        return dao;
    }

    private Message convertMessage(MessageDAO dao) {
        Message message = new Message(dao.id, dao.sender, dao.receiver, dao.messageText, dao.sentTs);
        return message;
    }

    public void postMessage(Message message) {
        repo.save(convertMessageDAO(message));
    }

    public List<Conversation> listConversations(Long userId) {
        List<MessageDAO> ungrouped = repo.findBySenderOrReceiver(userId);
        Map<Long, List<MessageDAO>> grouped = ungrouped.stream().collect(Collectors.groupingBy(message -> message.receiver.equals(userId) ? message.sender : message.receiver));
        ArrayList<Conversation> conversations = new ArrayList<>();
        for(Long otherId : grouped.keySet()) {
            List<MessageDAO> daos = grouped.get(otherId);
            List<Message> messages = daos.stream().map(this::convertMessage).collect(Collectors.toList());
            conversations.add(new Conversation(userId, otherId, messages));
        }
        return conversations;
    }
    
    
}
