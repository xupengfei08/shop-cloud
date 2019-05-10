package com.wp.cloud.shop.common.model.websocket;

import com.wp.cloud.shop.common.enumerate.WSMsgType;
import lombok.Data;

/**
 * Title: cloud-server--com.facemeng.sun.common.model.ws.WSClientMessage
 * Description: WebSocket客户端消息
 * Copyright: Copyright (c) 2018/01
 * Create DateTime: 2019/4/19 下午12:58
 *
 * @author suanmilk
 * @version v2
 */
@Data
public class WSClientMessage {

    private String id;

    private WSMsgType msgType;

    private Object data;
}
