package src.test.chat.controller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	// Endpoint 지정. = WebScoket Connection 경로
	// JS에서 이 경로로 들어와야 WebSocket이 연결된다.
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/websocket")
				.setAllowedOrigins("http://localhost:8000")
				.withSockJS()
				.setSupressCors(true);
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		// Topic 지정. (subscribe)
		// 해당 경로를 subscribe하는 Client에게 메세지를 전달하는 간단한 작업을 수행합니다.
		registry.enableSimpleBroker("/topic");
		
		// Client에서 Send 요청을 처리합니다. (publish)
		registry.setApplicationDestinationPrefixes("/pub");
		
	}
	
}
