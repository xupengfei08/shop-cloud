package com.wp.cloud.shop.constant;

/**
 * Title: cloud-server--com.facemeng.sun.common.constant.WebSocketConstant
 * Description: websocket常量
 * Copyright: Copyright (c) 2018/01
 * Create DateTime: 2018/1/20 上午12:12
 *
 * @author suanmilk
 * @version v2
 */
public interface WebSocketConstant {

    /**
     * 订阅消息前缀-广播信息
     */
    String DEST_PREFIX_TOPIC = "/topic";

    /**
     * 订阅消息前缀-客户信息
     */
    String DEST_PREFIX_CUSTOMER = "/customer";

    /**
     * 订阅消息前缀-用户信息
     */
    String DEST_PREFIX_USER = "/user";

    /**
     * 客户端发送消息前缀
     */
    String DEST_PREFIX_APP = "/app";
}
