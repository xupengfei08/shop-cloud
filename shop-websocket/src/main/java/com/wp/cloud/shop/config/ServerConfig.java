package com.wp.cloud.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.config.ServerConfig
 * @Description: 获取项目的ip和端口号
 * @Author suanmilk
 * @CreateTime: 2019-05-01 16:14
 */
@Component
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {

    @Value("${server.port}")
    private int serverPort;

    public String getHost() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return address.getHostAddress() + ":" + this.serverPort;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
//        this.serverPort = event.getWebServer().getPort();
    }
}
