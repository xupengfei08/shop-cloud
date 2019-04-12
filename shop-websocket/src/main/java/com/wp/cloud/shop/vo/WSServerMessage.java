package com.wp.cloud.shop.vo;

import lombok.Data;

/**
 * Title: cloud-server--com.facemeng.sun.common.model.ws.WSServerMessage
 * Description: WebSocket服务端广播消息
 * Copyright: Copyright (c) 2018/01
 * Create DateTime: 2018/1/19 下午12:58
 *
 * @author suanmilk
 * @version v2
 */
@Data
public class WSServerMessage {

    private String type;

    private String name;

    private Object message;

    public WSServerMessage(Object message) {
        this.message = message;
    }
}
