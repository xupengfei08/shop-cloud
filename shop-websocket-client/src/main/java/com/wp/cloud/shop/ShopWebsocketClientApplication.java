package com.wp.cloud.shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.ShopWebsocketClientApplication
 * @Description: websocket服务
 * @Author suanmilk
 * @CreateTime: 2019-04-11 11:12
 */
@SpringBootApplication
@Slf4j
public class ShopWebsocketClientApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ShopWebsocketClientApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("ws-client >>> 启动成功");
    }
}