package com.wp.cloud.shop.fallback.order;

import com.wp.cloud.shop.dto.order.OrderDto;
import com.wp.cloud.shop.fegin.order.OrderFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.order.fallback.OrderFeignFallBack
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-01-31 10:55
 */
@Component
@Slf4j
public class OrderFeignFallBack implements FallbackFactory<OrderFeign> {

    @Override
    public OrderFeign create(Throwable throwable) {
        return new OrderFeign() {

            @Override
            public Optional<OrderDto> getOrder(Long id) {
                log.error("请求熔断降级：", throwable);
                return Optional.empty();
            }

            @Override
            public Optional<OrderDto> createOrder(OrderDto order) {
                log.error("请求熔断降级：", throwable);
                return Optional.empty();
            }
        };
    }
}
