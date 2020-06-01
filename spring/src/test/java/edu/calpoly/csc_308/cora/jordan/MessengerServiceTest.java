package edu.calpoly.csc_308.cora.jordan;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import edu.calpoly.csc_308.cora.data.messages.MessageDAO;
import edu.calpoly.csc_308.cora.data.messages.MessageRepository;
import edu.calpoly.csc_308.cora.entities.Conversation;
import edu.calpoly.csc_308.cora.entities.Message;
import edu.calpoly.csc_308.cora.services.Messenger;


@AutoConfigureTestDatabase
@SpringBootTest
public class MessengerServiceTest {

  @Autowired
  private Messenger messenger;

  @Autowired
  private MessageRepository repo;

  @BeforeEach
  void setup() {
    repo.deleteAll();
  }

  @Test
  void testPostMessage() {
    assertThat(repo.findAll(), is(empty()));

    Message message = new Message(-1L, 4L, 5L, "test message", 1000L);
    message = messenger.postMessage(message);
    
    List<Message> storedMessages = repo.findAll().stream().map(Message::fromDAO).collect(Collectors.toList());

    assertThat(storedMessages, contains(new Message[]{message}));
  }

  @Test
  void testListConversations() {
    assertThat(repo.findAll(), is(empty()));

    List<MessageDAO> messages = new ArrayList<>();

    messages.add(repo.save(new MessageDAO(4L, 5L, "conversation 1: message 1", 1000L)));
    messages.add(repo.save(new MessageDAO(4L, 6L, "conversation 2: message 1", 2000L)));
    messages.add(repo.save(new MessageDAO(5L, 4L, "conversation 1: message 2", 1050L)));
    messages.add(repo.save(new MessageDAO(4L, 6L, "conversation 2: message 2", 2100L)));
    messages.add(repo.save(new MessageDAO(6L, 5L, "conversation 3: message 1", 2000L)));

    assertThat(repo.findAll(), contains(messages.toArray()));

    List<Conversation> expectedConversations = new ArrayList<>();
    expectedConversations.add(
      new Conversation(
        4L,
        5L, 
        Arrays.asList(
          Message.fromDAO(messages.get(0)),
          Message.fromDAO(messages.get(2))
        )));
    expectedConversations.add(
      new Conversation(
        4L,
        6L,
        Arrays.asList(
          Message.fromDAO(messages.get(1)),
          Message.fromDAO(messages.get(3))
        )));
    
    List<Conversation> actualConversations = messenger.listConversations(4L);

    assertThat(actualConversations, contains(expectedConversations.toArray()));
  }

}
