package com.wp.cloud.shop.filter;

import com.alibaba.fastjson.JSON;
import com.wp.cloud.shop.common.model.Result;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.filter.AuthFilter
 * @Description: 认证过滤器,
 * @Author suanmilk
 * @CreateTime: 2019-04-10 17:59
 */

@ConfigurationProperties("spring.cloud.gateway.ignore")
@Component
@Setter
public class AuthFilter implements GlobalFilter, Ordered {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private List<String> authPaths = new ArrayList();

    @Override
    public int getOrder() {
        return -2000;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        if (match(path)) {
            return chain.filter(exchange);
        }
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if ("token".equals(token)) {
            return chain.filter(exchange);
        }
        ServerHttpResponse response = exchange.getResponse();
        Result result = Result.builder().unauthorized("非法请求").build();
        byte[] datas = JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(datas);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    public boolean match(String requestUri) {
        for (String urlPattern : authPaths) {
            if (antPathMatcher.match(urlPattern, requestUri))
                return true;
        }
        return false;
    }
}
