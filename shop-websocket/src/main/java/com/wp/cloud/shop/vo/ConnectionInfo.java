package com.wp.cloud.shop.vo;

import com.wp.cloud.shop.ConnectionType;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.vo.AuthPrincipal
 * @Description: 用户认证信息
 * @Author suanmilk
 * @CreateTime: 2019-04-22 17:03
 */
@Data
public class ConnectionInfo {

    private ConnectionType type;

    private String id;

    private Date time;

    private Map<String, String> authInfo;

    public ConnectionInfo(ConnectionType type, String id, Date time, Map<String, String> authInfo) {
        this.type = type;
        this.id = id;
        this.time = time;
        this.authInfo = authInfo;
    }
}
