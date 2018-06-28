package com.netposa.springsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
@Service
public class WebSocketService {
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;
    /**
     * 广播
     * 发给所有在线用户
     *
     * @param msg
     */
    public void sendMsg(String msg) {
        template.convertAndSend("/topic/greetings", new Greeting("Hello, " + HtmlUtils.htmlEscape(msg) + "!"));
    }

    /**
     * 发送给指定用户
     * @param users
     * @param msg
     */
    public void send2Users(String user, String msg) {
//        users.forEach(userName -> {
//            template.convertAndSendToUser(userName, "/topic/greetings", msg);
//        });
        template.convertAndSendToUser(webAgentSessionRegistry.getSessionIds(user).stream().findFirst().get(), "/topic/greetings",new Greeting("Hello, " + HtmlUtils.htmlEscape(msg) + "!"));
    }
}
