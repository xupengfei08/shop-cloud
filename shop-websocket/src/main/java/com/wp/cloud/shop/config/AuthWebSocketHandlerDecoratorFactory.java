package com.wp.cloud.shop.config;

import com.wp.cloud.shop.service.IRedisSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import java.security.Principal;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.config.AuthWebSocketHandlerDecoratorFactory
 * @Description: 在连接建立时，保存websocket的session id，其中key为帐号名称；在连接断开时，从缓存中删除用户的sesionId值。此websocket sessionId值用于创建消息的路由键
 * @Author suanmilk
 * @CreateTime: 2019-04-22 16:10
 */
@Component
@Slf4j
public class AuthWebSocketHandlerDecoratorFactory implements WebSocketHandlerDecoratorFactory {

    @Autowired
    private IRedisSessionService redisSessionService;

    @Override
    public WebSocketHandler decorate(WebSocketHandler handler) {
        return new WebSocketHandlerDecorator(handler) {
            @Override
            public void afterConnectionEstablished(final WebSocketSession session) throws Exception {

                log.info("websocket online: {}, {}, {}", session.getId(), session.getUri(), session.getHandshakeHeaders());
                // 客户端与服务器端建立连接后，此处记录谁上线了
                Principal principal = session.getPrincipal();
                if (principal.getName() != null) {
                    String username = principal.getName();
                    redisSessionService.add(username, session.getId());
                }
                super.afterConnectionEstablished(session);
            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                log.info("websocket offline: {}", session.getId());
                // 客户端与服务器端断开连接后，此处记录谁下线了
                Principal principal = session.getPrincipal();
                if (principal.getName() != null) {
                    String username = session.getPrincipal().getName();
                    redisSessionService.del(username);
                }
                super.afterConnectionClosed(session, closeStatus);
            }
        };
    }
}
