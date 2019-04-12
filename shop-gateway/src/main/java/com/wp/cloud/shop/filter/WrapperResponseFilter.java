package com.wp.cloud.shop.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wp.cloud.shop.common.model.Result;
import lombok.Setter;
import org.reactivestreams.Publisher;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.filter.WrapperResponseFilter
 * @Description: 修改接口返回报文
 * @Author suanmilk
 * @CreateTime: 2019-04-10 17:48
 */

@ConfigurationProperties("spring.cloud.gateway.ignore")
@Component
@Setter
public class WrapperResponseFilter implements GlobalFilter, Ordered {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private List<String> wrapRespPaths = new ArrayList();

    @Override
    public int getOrder() {
        // -1 is response write filter, must be called before that
        return -3000;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        String path = exchange.getRequest().getPath().value();
        if (match(path)) {
            return chain.filter(exchange);
        }
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        // probably should reuse buffers
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        // 释放掉内存
                        DataBufferUtils.release(dataBuffer);
                        String rs = new String(content, Charset.forName("UTF-8"));
                        JSONObject jsonObject = JSON.parseObject(rs);
                        // 判断是否是异常
                        if (null != jsonObject.get("status") && null != jsonObject.get("error") && null != jsonObject.get("message")) {
                            throw new ResponseStatusException(HttpStatus.valueOf(jsonObject.getInteger("status")), jsonObject.getString("message"));
                        }
                        Result result = Result.builder().data(JSON.parseObject(rs)).ok().build();
                        byte[] newRs = JSON.toJSONString(result).getBytes(Charset.forName("UTF-8"));
                        originalResponse.getHeaders().setContentLength(newRs.length);//如果不重新设置长度则收不到消息。
                        return bufferFactory.wrap(newRs);
                    }));
                }
                // if body is not a flux. never got there.
                return super.writeWith(body);
            }
        };
        // replace response with decorator
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    public boolean match(String requestUri) {
        for (String urlPattern : wrapRespPaths) {
            if (antPathMatcher.match(urlPattern, requestUri))
                return true;
        }
        return false;
    }
}
