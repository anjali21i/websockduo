package com.core.websocket.websockduo;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import com.core.websocket.bean.Message;
import com.core.websocket.bean.MessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations msgTemplate;

    @EventListener
    public void handleDisconnectListener (SessionConnectEvent event) {
        //
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(StringUtils.hasText(username)) {
            log.info("username: {}", username);
            var message = Message.builder().type(MessageType.LEAVE).sender(username).build();
            msgTemplate.convertAndSend("/topic/public", message);
        }
    }
    
}
