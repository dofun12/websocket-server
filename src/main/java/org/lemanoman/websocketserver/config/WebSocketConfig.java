package org.lemanoman.websocketserver.config;

import org.lemanoman.websocketserver.HistoryService;
import org.lemanoman.websocketserver.repository.HistoryRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	private HistoryService historyRepository;
	public WebSocketConfig(HistoryService historyRepository){
		this.historyRepository = historyRepository;
	}

	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new SocketHandler(historyRepository), "/chat");
	}

}