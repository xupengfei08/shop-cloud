package com.wp.cloud.shop.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.config.AuthChannelInterceptor
 * @Description: 握手验证参数
 * @Author suanmilk
 * @CreateTime: 2019-04-22 16:31
 */
@Component
@Slf4j
public class AuthHandshakeInterceptor implements HandshakeInterceptor {

    /**
     * 握手前验证参数有合法性
     *
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        log.debug("beforeHandshake");
//        ((ServletServerHttpRequest) serverHttpRequest).getServletRequest().getHeader("");
//        if (serverHttpRequest.getPrincipal() == null)
//            throw new Exception("鉴权失败");
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        log.debug("afterHandshake");
    }

}
