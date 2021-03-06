package com.wp.cloud.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.config.SwaggerConfig
 * @Description: Swagger2配置类
 * @Author suanmilk
 * @CreateTime: 2019-02-14 11:31
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf());
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("基于SpringCloud Finchley.SR2搭建分布式购物系统")
                .description("购物系统接口文档说明")
                .termsOfServiceUrl("http://www.facemeng.com.cn")
                .contact(new Contact("suanmilk", "http://www.facemeng.com.cn", "xpf@facemeng.com.cn"))
                .version("1.0")
                .build();
    }
}
