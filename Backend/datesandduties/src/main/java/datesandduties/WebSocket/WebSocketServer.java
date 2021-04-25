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
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import datesandduties.Messages.Message;
import datesandduties.Messages.MessageRepository;

@ServerEndpoint("/websocket/{username}")
@Controller //@Component
public class WebSocketServer {
	
	//Tried just doing @Autowired
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
		
		//sendToMentionedUser(username, getMessageHistory());
		
		String message = "User: " + username + " has joined the chat";
		broadcast(message);
	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException {
		logger.info("Entered onMessage with Message:" + message);
		String username = sessionUserMap.get(session);

		if (message.startsWith("@")) {
			String sendToUsername = message.split(" ")[0].substring(1);
			sendToMentionedUser(sendToUsername, "[DM] " + username + ": " + message);
			sendToMentionedUser(username, "[DM] " + username + ": " + message);
		} else {
			broadcast(username + ": " + message);

		}
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
	/*private String getMessageHistory() {
		List<Message> messages = messageRepo.findAll();
		
		StringBuilder sb = new StringBuilder();
		
		if(messages != null && messages.size() != 0) {
			for (Message message : messages) {
				sb.append(message.getUsername() + ": " + message.getMessageContent() + "\n");
			}
		}
		return sb.toString();

		
	}*/

}
