package datesandduties.Messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping(path = "/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(path = "/all")
    public Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @GetMapping(path = "/findMessage/{username}")
    public Message findByUsername(@PathVariable String username) {
        return messageRepository.findByUsername(username);
    }

    @DeleteMapping(path = "/deleteAll")
    public String deleteAllMessages() {
        messageRepository.deleteAll();
        return "Deleted All Messages in Database";
    }

    @Transactional
    @DeleteMapping(path = "/deleteHistory")
    public void deleteByUsername(@PathVariable String username) {
        deleteByUsername(username);
    }

}
