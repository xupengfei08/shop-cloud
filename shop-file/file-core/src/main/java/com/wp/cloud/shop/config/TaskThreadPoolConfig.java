package com.wp.cloud.shop.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Title: cloud-server--com.facemeng.sun.local.face.component.config.TaskThreadPoolConfig
 * Description: 线程池配置文件信息
 * Copyright: Copyright (c) 2018/05
 * Create DateTime: 2018/5/29 下午4:50
 *
 * @author suanmilk
 * @version v2
 */
@Configuration
@ConfigurationProperties(prefix = "spring.task.pool")
@Data
public class TaskThreadPoolConfig {

    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;

}
