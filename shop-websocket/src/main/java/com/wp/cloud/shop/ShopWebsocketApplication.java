package com.wp.cloud.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.ShopWebsocketApplication
 * @Description: websocket服务
 * @Author suanmilk
 * @CreateTime: 2019-04-11 11:12
 */
@SpringBootApplication
@EnableEurekaClient
public class ShopWebsocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopWebsocketApplication.class, args);
    }
}