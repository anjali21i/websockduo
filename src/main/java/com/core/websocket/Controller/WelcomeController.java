package com.core.websocket.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.core.websocket.bean.Message;
import com.core.websocket.bean.Welcome;

@Controller
public class WelcomeController {

    @MessageMapping("/welcome")
    @SendTo("/topic/welcomeme")
    public Welcome welcome(Message msg) {
        return new Welcome(HtmlUtils.htmlEscape(msg.getMessages()));   
    }
    
}
