package com.wp.cloud.shop;

import com.wp.cloud.shop.service.user.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class ShopUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopUserApplication.class, args);
    }

    /**
     * 初始化用户信息
     * 注：Spring Boot2不能像1.x一样，用spring.datasource.schema/data指定初始化SQL脚本，否则与actuator不能共存
     * 原因详见：
     * https://github.com/spring-projects/spring-boot/issues/13042
     * https://github.com/spring-projects/spring-boot/issues/13539
     *
     * @param userService
     * @return runner
     */
    @Bean
    ApplicationRunner init(UserService userService) {
        return args -> {
            userService.count();
        };
    }

}

