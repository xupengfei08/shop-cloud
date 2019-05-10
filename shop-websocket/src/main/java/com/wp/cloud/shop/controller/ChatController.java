package com.wp.cloud.shop.controller;

import com.wp.cloud.shop.common.utils.MD5Util;
import com.wp.cloud.shop.config.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.controller.ChatController
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-04-11 13:26
 */
@Slf4j
@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private AmqpTemplate amqpTemplate;
//
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ServerConfig serverConfig;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    //向redis消息队列index通道发布消息
    @Scheduled(fixedRate = 300000)
    public void sendMessage() {
        String serverHostMD5 = MD5Util.encodeByMD5(serverConfig.getHost());
        String channel = "topic.ws." + serverHostMD5;
        stringRedisTemplate.convertAndSend(channel, serverConfig.getHost());
    }

}
