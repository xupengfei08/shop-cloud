package com.wp.cloud.shop.config;

import com.wp.cloud.shop.constant.WebSocketConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.config.WebSocketAutoConfig
 * @Description: websocket配置类
 * @Author suanmilk
 * @CreateTime: 2019-04-11 13:23
 */
@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class WebSocketAutoConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private AuthWebSocketHandlerDecoratorFactory authWebSocketHandlerDecoratorFactory;

    @Autowired
    private AuthPrincipalHandshakeHandler authPrincipalHandshakeHandler;

    @Autowired
    private AuthHandshakeInterceptor authHandshakeInterceptor;

    /**
     * 添加一个服务端点，来接收客户端的连接。将 "/endpointChat" 路径注册为 STOMP 端点
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 在浏览器网页上访问：http://网关IP:网关端口/chat
        // Java-WebSocket（java原生websocket客户端）访问：ws://网关IP:网关端口/chat/websocket
        registry.addEndpoint("/endpointChat")
                .addInterceptors(authHandshakeInterceptor)
                .setHandshakeHandler(authPrincipalHandshakeHandler)
                .setAllowedOrigins("*") // 允许跨域
                .withSockJS();
    }

    /**
     * 配置了一个简单的消息代理，通俗一点讲就是设置消息连接请求的各种规范信息
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        定义了一个（或多个）客户端订阅地址的前缀信息，也就是客户端接收服务端发送消息的前缀信息
        registry.enableSimpleBroker(
                new String[]{
                        WebSocketConstant.DEST_PREFIX_TOPIC,
                        WebSocketConstant.DEST_PREFIX_CUSTOMER,
                        WebSocketConstant.DEST_PREFIX_USER
                });
//        定义了服务端接收地址的前缀，也即客户端给服务端发消息的地址前缀
        registry.setApplicationDestinationPrefixes(WebSocketConstant.DEST_PREFIX_APP);
//        点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
//        registry.setUserDestinationPrefix("/user/");

    }

    /**
     * 这时实际spring weboscket集群的新增的配置，用于获取建立websocket时获取对应的sessionid值
     *
     * @param registration
     */
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.addDecoratorFactory(authWebSocketHandlerDecoratorFactory);
    }

    /**
     * 授权访问验证
     *
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                log.info("webSocket {}：{}", accessor.getCommand(), message);
                // 创建连接时进行验证
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String token = "token";

                    //过滤不需要验证token的路径
                    if ("/user/alertNotice".equals(token)) {
                        return message;
                    }


                    if (StringUtils.isEmpty(token)) {
                        log.error("webSocket token is 不存在");
                    }
                }
                return message;
            }
        });
    }

}
