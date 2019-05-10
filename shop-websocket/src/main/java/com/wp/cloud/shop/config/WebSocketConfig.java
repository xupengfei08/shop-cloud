package com.wp.cloud.shop.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.config.WebSocketAutoConfig
 * @Description: websocket配置类
 * @Author suanmilk
 * @CreateTime: 2019-04-11 13:23
 */
@Configuration
@EnableWebSocket
@Slf4j
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ShopWebSocketHandler webSocketHandler;

    @Autowired
    private AuthPrincipalHandshakeHandler authPrincipalHandshakeHandler;

    @Autowired
    private AuthHandshakeInterceptor authHandshakeInterceptor;

    /**
     * 第一个addHandler是对正常连接的配置，
     * 第二个是如果浏览器不支持websocket，使用socketjs模拟websocket的连接。
     *
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/webSocketServer")
//                .setHandshakeHandler(authPrincipalHandshakeHandler)
//                .addInterceptors(authHandshakeInterceptor)
                .setAllowedOrigins("*");
        registry.addHandler(webSocketHandler, "/sockjs/webSocketServer")
                .setHandshakeHandler(authPrincipalHandshakeHandler)
                .addInterceptors(authHandshakeInterceptor)
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
