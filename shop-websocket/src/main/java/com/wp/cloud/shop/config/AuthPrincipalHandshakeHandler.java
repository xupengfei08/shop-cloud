package com.wp.cloud.shop.config;

import com.wp.cloud.shop.vo.AuthPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.config.AuthPrincipalHandshakeHandler
 * @Description: 处理websocket请求，这里只重写determineUser方法，生成自定义AuthPrincipal ，使用token标记登录用户，而不是默认值
 * @Author suanmilk
 * @CreateTime: 2019-04-22 17:06
 */
@Component
@Slf4j
public class AuthPrincipalHandshakeHandler extends DefaultHandshakeHandler {

    /**
     * @param request
     * @param wsHandler
     * @param attributes
     * @return
     */
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {

        log.info("determineUser");
        ServletServerHttpRequest req = (ServletServerHttpRequest) request;
        // 是否存在token信息
        String token = req.getServletRequest().getParameter("token");
        if (StringUtils.isEmpty(token)) {
            token = req.getServletRequest().getHeader("token");
            int a = 2 / 0;
        }
        if (StringUtils.isNotEmpty(token)) {
            // TODO 根据token获取用户信息
            String userId = "facemeng";
            return new AuthPrincipal(userId);
        }
        // token 不存在，获取客户信息
        String customerId = req.getServletRequest().getParameter("customerId");
        return new AuthPrincipal(customerId);
    }
}
