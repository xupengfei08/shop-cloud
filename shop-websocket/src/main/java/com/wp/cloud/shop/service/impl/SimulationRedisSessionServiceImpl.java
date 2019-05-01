package com.wp.cloud.shop.service.impl;

import com.wp.cloud.shop.service.IRedisSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.service.impl.SimulationRedisSessionServiceImpl
 * @Description: 将用户名称和websocket sessionId的关系存储到redis，提供添加、删除、查询
 * @Author suanmilk
 * @CreateTime: 2019-04-22 16:04
 */
@Component
public class SimulationRedisSessionServiceImpl implements IRedisSessionService {

    @Autowired
    private RedisTemplate<String, String> template;

    // key = 登录用户名称， value=websocket的sessionId
    private ConcurrentHashMap<String, String> redisHashMap = new ConcurrentHashMap<>(32);


    /**
     * 在缓存中保存用户和websocket sessionid的信息
     *
     * @param name
     * @param wsSessionId
     */
    public void add(String name, String wsSessionId) {
        BoundValueOperations<String, String> boundValueOperations = template.boundValueOps(name);
        boundValueOperations.set(wsSessionId, 24 * 3600, TimeUnit.SECONDS);
    }

    /**
     * 从缓存中删除用户的信息
     *
     * @param name
     */
    public boolean del(String name) {
        return template.execute(connection -> {
            byte[] rawKey = template.getStringSerializer().serialize(name);
            return connection.del(rawKey) > 0;
        }, true);
    }

    /**
     * 根据用户id获取用户对应的sessionId值
     *
     * @param name
     * @return
     */
    public String get(String name) {
        BoundValueOperations<String, String> boundValueOperations = template.boundValueOps(name);
        return boundValueOperations.get();
    }
}
