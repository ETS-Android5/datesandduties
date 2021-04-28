package datesandduties.Messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MessageRepository extends JpaRepository<Message, Integer> {
   public Message findByUsername(String username);
   
   @Modifying
   @Query(value = "DELETE FROM Message e WHERE e.username = :username")
   public void deleteByUsername(@Param("username")String username);
}
