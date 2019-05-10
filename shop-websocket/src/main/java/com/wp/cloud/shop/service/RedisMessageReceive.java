package com.wp.cloud.shop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.service.RedisMessageReceive
 * @Description: Redis消息队列监听消息处理
 * @Author suanmilk
 * @CreateTime: 2019-05-01 16:07
 */
@Service
@Slf4j
public class RedisMessageReceive {

    public void handleMessage(String message) {
        log.debug("消息来了：{}", message);
        // TODO 这里是收到通道的消息之后执行的方法
    }
}
