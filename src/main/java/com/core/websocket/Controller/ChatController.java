package com.core.websocket.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.core.websocket.bean.Message;
import com.core.websocket.bean.Welcome;

@Controller
public class ChatController {

    @MessageMapping("/welcome")
    @SendTo("/topic/welcomeme")
    public Welcome welcome(Message msg) {
        System.out.println("inside welome controller ");
        return new Welcome(HtmlUtils.htmlEscape(msg.getContent()));   
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message msg) {
        return msg;
    }

    @MessageMapping("/addUser")
    @SendTo("/topic/public")
    public Message adduser(@Payload Message msg, SimpMessageHeaderAccessor accessor) {
        //add user in web socket session
        accessor.getSessionAttributes().put("username", msg.getSender());
        return msg;
    }
    
}
