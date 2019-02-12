package com.wp.cloud.shop.fegin.order;

import com.wp.cloud.shop.dto.order.OrderDto;
import com.wp.cloud.shop.fallback.order.OrderFeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.order.fegin.OrderFeign
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-01-31 10:50
 */
@RequestMapping("/order")
@FeignClient(name = "shop-order-service", fallbackFactory = OrderFeignFallBack.class)
public interface OrderFeign {

    /**
     * 根据ID查询订单信息
     *
     * @return
     */
    @GetMapping("/{id}")
    Optional<OrderDto> getOrder(@PathVariable("id") Long id);


    /**
     * 创建订单
     *
     * @return
     */
    @PostMapping
    Optional<OrderDto> createOrder(@RequestBody OrderDto order);

}
