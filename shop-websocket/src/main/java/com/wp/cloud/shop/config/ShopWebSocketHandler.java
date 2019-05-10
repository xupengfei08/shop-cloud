package com.wp.cloud.shop.config;

import com.alibaba.fastjson.JSON;
import com.wp.cloud.shop.ConnectionType;
import com.wp.cloud.shop.common.enumerate.WSMsgType;
import com.wp.cloud.shop.common.model.websocket.WSClientMessage;
import com.wp.cloud.shop.service.RedisService;
import com.wp.cloud.shop.vo.ConnectionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.config.AuthWebSocketHandler
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-04-22 16:10
 */
@Component
@Slf4j
public class ShopWebSocketHandler extends TextWebSocketHandler {

    private static ConcurrentHashMap<String, WebSocketSession> websocketSessions = new ConcurrentHashMap<>();

    @Autowired
    private RedisService redisService;

    @Override
    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
        log.info("websocket online: {}", session.getId());
        session.setTextMessageSizeLimit(80000);
        HttpHeaders headers = session.getHandshakeHeaders();
        log.debug("HttpHeaders：{}", JSON.toJSONString(headers, true));
        // 验证参数是否存在
        if (!checkHeaders(headers))
            throw new Exception("鉴权失败");

        ConnectionType type = ConnectionType.CUSTOMER;

        String id = headers.getFirst("connect-id");

        Map<String, String> authInfo = null;

        ConnectionInfo connectionInfo = new ConnectionInfo(type, id, new Date(), authInfo);
        websocketSessions.put(id, session);

        // redis缓存key名：
        String key = "WEBSOCKET:CONNECTION:" + type + ":" + id;

        redisService.set(key, connectionInfo);
        log.debug("LocalAddress：{}", session.getLocalAddress());
        log.debug("TextMessageSizeLimit：{}", session.getTextMessageSizeLimit());
        session.sendMessage(new TextMessage("hello client"));
    }

    private boolean checkHeaders(HttpHeaders headers) {
        if (!headers.containsKey("connect-id"))
            return false;
        if (!headers.containsKey("connect-type"))
            return false;
        return true;
    }

    /**
     * 处理用户请求
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("系统处理用户的请求信息：{}", message.getPayload());
        WSClientMessage clientMessage = JSON.parseObject(message.getPayload(), WSClientMessage.class);
        WSMsgType msgType = clientMessage.getMsgType();
        if (msgType != null) {
            switch (msgType) {
                case PING:
                    // TODO 处理客户端心跳请求

                    break;
                case UPLOAD_SNAP:
                    // TODO 处理客户端抓拍上传请求

                    break;
                default:
                    // 未定义的消息类型不作处理
                    log.warn("未定义的消息类型：{}", msgType);
                    break;
            }
        }


    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("websocket offline: {}", session.getId(), closeStatus);
        // 客户端与服务器端断开连接后，此处记录谁下线了

        HttpHeaders headers = session.getHandshakeHeaders();


        ConnectionType type = ConnectionType.CUSTOMER;

        String id = headers.getFirst("connect-id");


        websocketSessions.remove(id);

        // redis缓存key名：
        String key = "WEBSOCKET:CONNECTION:" + type + ":" + id;

        redisService.del(key);
    }

    /**
     * 自定义函数
     * 给所有的在线用户发送消息
     */
    public void sendMessagesToUsers(TextMessage message) {
//        for (WebSocketSession user : userList) {
//            try {
//                // isOpen()在线就发送
//                if (user.isOpen()) {
//                    user.sendMessage(message);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                log.error(e.getLocalizedMessage());
//            }
//        }
    }

    /**
     * 自定义函数
     * 发送消息给指定的在线用户
     */
    public void sendMessageToUser(String userId, TextMessage message) {
//        for (WebSocketSession user : userList) {
//            if (user.getAttributes().get("userId").equals(userId)) {
//                try {
//                    // isOpen()在线就发送
//                    if (user.isOpen()) {
//                        user.sendMessage(message);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    log.error(e.getLocalizedMessage());
//                }
//            }
//        }
    }
}
