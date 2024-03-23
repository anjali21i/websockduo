package com.core.websocket.websockduo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
public class SocketConfig implements WebSocketMessageBrokerConfigurer{

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // TODO Auto-generated method stub
        WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // TODO Auto-generated method stub
        WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
    }



    
}
