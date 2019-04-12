package com.wp.cloud.shop.controller.order;

import com.wp.cloud.shop.dto.order.OrderDto;
import com.wp.cloud.shop.dto.user.UserDto;
import com.wp.cloud.shop.fegin.order.OrderFeign;
import com.wp.cloud.shop.fegin.user.UserFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(description = "订单管理")
public class OrderController implements OrderFeign {

    @Autowired
    private UserFeign userFeign;

    @Override
    @ApiOperation(value = "根据ID查询订单信息",notes = "根据ID查询订单信息")
    public Optional<OrderDto> getOrder(@PathVariable @ApiParam(value = "订单ID",required = true, example = "10") Long id) {
        try {
            Thread.sleep(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Optional.of(new OrderDto());
    }

    @Override
    @ApiOperation(value = "创建订单",notes = "创建订单")
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
