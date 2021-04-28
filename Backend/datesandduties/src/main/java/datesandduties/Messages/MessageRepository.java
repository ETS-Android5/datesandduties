package datesandduties.Messages;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
   public Message findByUsername(String username);
}
