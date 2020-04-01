package org.grant.zm.spring2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * grant
 * 24/3/2020 2:27 下午
 * 描述：
 */
@ServerEndpoint("/wPath")
@Component
@Slf4j
public class WebSocketAction {

    @OnOpen
    public void onOpen(Session session) throws IOException {
        log.info("连接打开");
        session.getBasicRemote().sendText("上线了");
    }

    @OnClose
    public void onClose(){
        log.info("下线");
    }

    @OnMessage
    public void onMessage(String message, Session session){
        log.info("收到消息：{}", message);
    }
}
