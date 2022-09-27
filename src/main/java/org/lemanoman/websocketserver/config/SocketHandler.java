package org.lemanoman.websocketserver.config;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.lemanoman.websocketserver.HistoryModel;
import org.lemanoman.websocketserver.HistoryService;
import org.lemanoman.websocketserver.repository.HistoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

@Component
public class SocketHandler extends TextWebSocketHandler {

    HistoryService historyRepository;
    final Gson gson = new Gson();
    HashMap<String,WebSocketSession> sessions = new HashMap<>();

    public SocketHandler(HistoryService historyRepository){
        this.historyRepository = historyRepository;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
    }
	private void broadcast(String message){

		for (Map.Entry<String, WebSocketSession> entry : sessions.entrySet()) {
			try {
                final WebSocketSession webSocketSession = entry.getValue();

				webSocketSession.sendMessage(new TextMessage(message));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}



    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        String receivedJson = message.getPayload();
        Message messageReceived = null;
        try {
           messageReceived  =  gson.fromJson(receivedJson,Message.class);
        }catch (JsonSyntaxException exception){
            Message response = new Message();
            response.setEvent("error");
            response.setSenderName("Server");
            response.setMessage("Invalid Message");
            response.setClientId(null);
            session.sendMessage(new TextMessage(gson.toJson(response)));
            return;
        }
        Message messageData = new Message();
        messageData.setMessage(messageReceived.getMessage());
        messageData.setClientId(session.getId());
        messageData.setSenderName(messageReceived.getSenderName());
        messageData.setEvent("message");
        historyRepository.saveMax(new HistoryModel(messageData),30);
        broadcast(gson.toJson(messageData));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //the messages will be broadcasted to all users.
        sessions.put(session.getId(),session);

    }

}