package com.wp.cloud.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.ShopWebsocketApplication
 * @Description: websocket服务
 * @Author suanmilk
 * @CreateTime: 2019-04-11 11:12
 */
@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
public class ShopWebsocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopWebsocketApplication.class, args);
    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        return taskScheduler;
    }
}