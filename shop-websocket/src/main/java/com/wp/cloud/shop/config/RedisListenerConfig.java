package com.wp.cloud.shop.config;

import com.wp.cloud.shop.common.utils.MD5Util;
import com.wp.cloud.shop.service.RedisMessageReceive;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.config.RedisListenerConfig
 * @Description: Redis消息监听配置
 * @Author suanmilk
 * @CreateTime: 2019-05-01 15:53
 */
@Configuration
public class RedisListenerConfig {
    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     *
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter, ServerConfig serverConfig) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //可以添加多个 messageListener
        String serverHostMD5 = MD5Util.encodeByMD5(serverConfig.getHost());
        String channel = "topic.ws." + serverHostMD5;
        container.addMessageListener(listenerAdapter, new PatternTopic(channel));
        return container;
    }


    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     *
     * @param messageReceive
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(RedisMessageReceive messageReceive) {
        return new MessageListenerAdapter(messageReceive, "handleMessage");
    }

}
