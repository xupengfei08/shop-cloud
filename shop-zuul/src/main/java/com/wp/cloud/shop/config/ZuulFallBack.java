package com.wp.cloud.shop.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Title: cloud-test--com.wpline.cloud.zuul.config.ZuulFallBack
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-01-27 20:55
 */
@Component
@Slf4j
public class ZuulFallBack implements FallbackProvider {

    @Override
    public String getRoute() {
        return null; // 服务id :如果需要所有调用都支持回退，则return "*" 或return null
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        // 定义RESPONSE消息体
        JSONObject json = new JSONObject();
        HttpStatus httpStatus = HttpStatus.REQUEST_TIMEOUT;
        json.put("code", httpStatus.value());
        json.put("serviceId", route);
        json.put("msg", httpStatus.getReasonPhrase());
        log.error("网关路由请求失败：{}", json.toJSONString());
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK; // 请求网关成功了，所以是ok
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(json.toJSONString().getBytes("UTF-8")); //返回前端的内容
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8); //设置头
                return httpHeaders;
            }
        };
    }
}
