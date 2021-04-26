package datesandduties.WebSocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import datesandduties.Messages.Message;
import datesandduties.Messages.MessageRepository;

@ServerEndpoint("/websocket/{username}")
@Controller // @Component
public class WebSocketServer {

	// Tried just doing @Autowired
	// and private MessageRepository, but that stated that the repo was null.
	private static MessageRepository messageRepo;

	@Autowired
	public void setMessageRepository(MessageRepository msgrepo) {
		messageRepo = msgrepo;
	}

	private static Map<Session, String> sessionUserMap = new HashMap<>();
	private static Map<String, Session> userSessionMap = new HashMap<>();
	private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException {
		logger.info("Entered onOpen");

		sessionUserMap.put(session, username);
		userSessionMap.put(username, session);

		String message = "User: " + username + " has joined the chat";
		broadcast(message);

		// What happens here is that the user's prior message History is shown.
		String chatHistory = getMessageHistory(username);
		sendToMentionedUser(username, chatHistory);
	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException {
		logger.info("Entered onMessage with Message:" + message);
		String username = sessionUserMap.get(session);

		if (message.startsWith("@")) {
			String sendToUsername = message.split(" ")[0].substring(1);
			sendToMentionedUser(sendToUsername, "[DM] " + username + ": " + message);
			sendToMentionedUser(username, message);
		} else {
			broadcast(username + ": " + message);

		}
		// This is the line that actually saves the message to the repository - in my
		// previous tests, I didn't have this line.
		messageRepo.save(new Message(username, message));
	}

	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Entered onClose");

		String username = sessionUserMap.get(session);
		sessionUserMap.remove(session);
		userSessionMap.remove(username);

		String message = username + " has disconnected";
		broadcast(message);

	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		logger.info("Entered onError");
		throwable.printStackTrace();
	}

	private void sendToMentionedUser(String username, String message) {
		try {
			userSessionMap.get(username).getBasicRemote().sendText(message);
		} catch (IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}
	}

	private void broadcast(String message) {
		sessionUserMap.forEach((session, username) -> {
			try {
				session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				logger.info("Exception: " + e.getMessage().toString());
				e.printStackTrace();
			}

		});

	}

	private String getMessageHistory(String username) {
		List<Message> messages = messageRepo.findAll();
		// This way of building a string is way easier
		String returnString = "";

		if (messages != null && messages.size() != 0) {
			for (Message message : messages) {
				if (message.getUsername().equals(username)) {
					returnString += message.getDate().toString() + " " + message.getUsername() + ": " + message.getMessageContent() + "\n";
				}
			}
		}
		logger.info(returnString);
		return returnString;
	
		//In the log, the text is all jumbled together regardless of how I format it. At least for the websocket.org echo test site. 
	}

}
