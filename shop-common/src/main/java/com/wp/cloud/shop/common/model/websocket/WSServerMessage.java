package com.wp.cloud.shop.common.model.websocket;

import com.wp.cloud.shop.common.enumerate.WSMsgType;
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

    private String msgId;

    private WSMsgType type;

    private Object data;
}
