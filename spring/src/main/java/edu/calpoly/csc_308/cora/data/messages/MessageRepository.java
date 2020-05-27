package edu.calpoly.csc_308.cora.data.messages;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<MessageDAO, Long> {

    @Query("select m from MessageDAO m where m.sender = ?1 or m.receiver = ?1")
    List<MessageDAO> findBySenderOrReceiver(Long id);
}
