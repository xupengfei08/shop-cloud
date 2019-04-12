package com.wp.cloud.shop.config;

import com.wp.cloud.shop.constant.WebSocketConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.config.WebSocketAutoConfig
 * @Description: websocket配置类
 * @Author suanmilk
 * @CreateTime: 2019-04-11 13:23
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketAutoConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 配置消息代理(Message Broker)
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        订阅Broker名称
//        用户订阅主题的前缀
//        /topic 代表发布平台广播，即群发
//        /customer 代表发布公司广播，即发指定用户
//        /user 代表点对点，即发指定用户
        registry.enableSimpleBroker(
                new String[]{
                        WebSocketConstant.DEST_PREFIX_TOPIC,
                        WebSocketConstant.DEST_PREFIX_CUSTOMER,
                        WebSocketConstant.DEST_PREFIX_USER
                });
//        全局使用的消息前缀（客户端订阅路径上会体现出来）
        registry.setApplicationDestinationPrefixes(WebSocketConstant.DEST_PREFIX_APP);
//        点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
//        registry.setUserDestinationPrefix("/user/");
    }

    /**
     * 注册STOMP协议的节点(endpoint),并映射指定的url
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 在网页上我们可以通过http://ip:port/chat来和服务器的WebSocket连接
        registry.addEndpoint("/chat")
//                .addInterceptors(new HandshakeInterceptor() {
//                    /**
//                     * websocket握手
//                     */
//                    @Override
//                    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
//                        ServletServerHttpRequest req = (ServletServerHttpRequest) request;
//                        //获取token认证
//                        String token = req.getServletRequest().getParameter("token");
//                        //解析token获取用户信息
//                        log.info("webSocket token is {}", token);
//                        if (StringUtils.isEmpty(token)) {
//                            log.warn("非法访问");
//                            throw new CommonException("非法访问");
//                        }
//                        if (!"facemeng".equals(token)) {
//                            String key = RedisConstant.USER_INFO_PREFIX + token;
//                            // 验证token
//                            if (!redisDao.exists(key)) {
//                                log.warn("webSocket token：{} 已失效", token);
//                                throw new CommonException("token已失效");
//                            }
//                        }
//                        return true;
//                    }
//
//                    @Override
//                    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
//                        log.debug("验证成功，创建连接");
//                    }
//                }).setHandshakeHandler(new DefaultHandshakeHandler())
                .setAllowedOrigins("*") // 允许跨域
                .withSockJS();
//                .setClientLibraryUrl("//cdn.jsdelivr.net/sockjs/1/sockjs.min.js"); // 允许使用socketJs方式访问（http://）
    }

    /**
     * 授权访问验证
     *
     * @param registration
     */
//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(new ChannelInterceptorAdapter() {
//            @Override
//            public Message<?> preSend(Message<?> message, MessageChannel channel) {
//                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//                // 创建连接时进行验证
//                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//                    String token = accessor.getFirstNativeHeader(SystemConstant.TOKEN_HEADER);
//
//                    log.info("webSocket token is {}", token);
//
//                    //过滤不需要验证token的路径
//                    if ("/user/alertNotice".equals(token)){
//                        return message;
//                    }
//
//
//                    if (StringUtils.isEmpty(token)) {
//                        throw new CommonException("非法访问");
//                    }
//                    if (!"facemeng".equals(token)) {
//                        String key = RedisConstant.USER_INFO_PREFIX + token;
//                        // 验证token
//                        if (!redisDao.exists(key))
//                            throw new CommonException("token已失效");
//                    }
//                }
//                return message;
//            }
//        });
//    }

}
