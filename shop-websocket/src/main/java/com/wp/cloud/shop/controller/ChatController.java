package com.wp.cloud.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.wp.cloud.shop.vo.WSClientMessage;
import com.wp.cloud.shop.vo.WSServerMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.controller.ChatController
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-04-11 13:26
 */
@Slf4j
@Controller
@EnableScheduling
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 测试web发送消息到服务器
     *
     * @param id
     * @param headers
     * @param message
     * @return
     */
    @MessageMapping("/send/{id}")
    @SendTo("/topic/send")
    public WSServerMessage send(@DestinationVariable("id") String id,
                                @Headers Map headers,
                                @Payload WSClientMessage message) {
        log.info("id : {}", id);
        log.info("headers : ", JSONObject.toJSONString(headers));
        return new WSServerMessage("测试成功");
    }

    /**
     * 当浏览器向服务端发送请求时,通过@MessageMapping映射/welcome这个地址,类似于@ResponseMapping
     * 当服务器有消息时,会对订阅了@SendTo中的路径的浏览器发送消息
     *
     * @param clientMessage
     * @return
     */
    @MessageMapping("/welcome")
    @SendTo("/topic/broadcast")
    public WSServerMessage welcome(WSClientMessage clientMessage) {
        //方法用于广播测试
        System.out.println("clientMessage.getName() = " + clientMessage.getMessage().toString());
        return new WSServerMessage("Welcome , " + clientMessage.getMessage().toString() + " !");
    }

    /**
     * 每一分钟校验服务器时间
     *
     * @return
     */
    @Scheduled(fixedRate = 60000)
    public Object callback() {
        messagingTemplate.convertAndSend("/topic/system_time", System.currentTimeMillis());
        return null;
    }

    /**
     * 发送报警推送网页消息给指定用户
     */
    @MessageMapping("/alertNotice")
    public WSServerMessage alertNotice() {

        messagingTemplate.convertAndSendToUser("145", "user/" + 146 + "/alertNotice",
                new WSServerMessage("警报信息 , aaaa " + " !"));
        //方法用于发送报警推送网页消息给指定用户
        System.out.println("网页警报信息 = aaaa");
        return null;
    }

}
