package com.netposa.springsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

public class STOMPDisConnectEventListener implements ApplicationListener<SessionDisconnectEvent> {

    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        //login get from browser
        String agentId = sha.getNativeHeader("userId").get(0);
        String sessionId = sha.getSessionId();
        webAgentSessionRegistry.unregisterSessionId(agentId,sessionId);
        
    }
}
