package com.wp.cloud.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.ShopZuulApplication
 * @Description: 网关服务
 * @Author suanmilk
 * @CreateTime: 2019-02-01 17:13
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ShopZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopZuulApplication.class, args);
    }
}