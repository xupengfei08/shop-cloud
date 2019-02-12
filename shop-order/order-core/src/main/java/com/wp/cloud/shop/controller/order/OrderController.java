package com.wp.cloud.shop.controller.order;

import com.wp.cloud.shop.dto.order.OrderDto;
import com.wp.cloud.shop.dto.user.UserDto;
import com.wp.cloud.shop.fegin.order.OrderFeign;
import com.wp.cloud.shop.fegin.user.UserFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.order.controller.OrderController
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-01-31 11:10
 */
@RestController
@Slf4j
public class OrderController implements OrderFeign {

    @Autowired
    private UserFeign userFeign;

    @Override
    public Optional<OrderDto> getOrder(@PathVariable Long id) {
        return Optional.of(new OrderDto());
    }

    @Override
    public Optional<OrderDto> createOrder(@RequestBody OrderDto order) {
        Assert.notNull(order.getUserId(), "订单所属用户ID不为空");
        UserDto userDto = userFeign.getUser(order.getUserId());
        if (userDto != null && userDto.getVip())
            log.info("VIP用户创建订单");
        else {
            log.error("用户非VIP，不可下单");
        }
        return Optional.of(order);
    }
}
