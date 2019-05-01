package com.wp.cloud.shop.vo;

import java.security.Principal;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.vo.AuthPrincipal
 * @Description: 用户认证信息
 * @Author suanmilk
 * @CreateTime: 2019-04-22 17:03
 */
public class AuthPrincipal implements Principal {

    private String token;

    public AuthPrincipal(String token) {
        this.token = token;
    }

    @Override
    public String getName() {
        return token;
    }
}
