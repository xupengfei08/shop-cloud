package com.wp.cloud.shop.vo;

import lombok.Data;

/**
 * Title: cloud-server--com.facemeng.sun.common.model.ws.WSClientMessage
 * Description: WebSocket客户端消息
 * Copyright: Copyright (c) 2018/01
 * Create DateTime: 2018/1/19 下午12:58
 *
 * @author suanmilk
 * @version v2
 */
@Data
public class WSClientMessage {

    private Long userId;

    private String type;

    private Object message;
}
