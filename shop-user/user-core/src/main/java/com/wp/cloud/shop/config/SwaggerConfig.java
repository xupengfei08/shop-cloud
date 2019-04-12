package com.wp.cloud.shop.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

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
        // 使用swagger往往会和JWT一起使用，一般使用jwt会将token放在head里
        ParameterBuilder tokenPar = new ParameterBuilder();

        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token").description("TOKEN令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("基于SpringCloud Finchley.SR2搭建分布式购物系统")
                .description("用户接口文档说明")
                .termsOfServiceUrl("http://www.facemeng.com.cn")
                .contact(new Contact("suanmilk", "http://www.facemeng.com.cn", "xpf@facemeng.com.cn"))
                .version("2.0")
                .build();
    }
}
