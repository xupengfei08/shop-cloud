package com.wp.cloud.shop.entity.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.order.entity.Order
 * @Description: 订单实体类
 * @Author suanmilk
 * @CreateTime: 2019-01-31 11:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;

    private String goodsName;

    private BigDecimal price;

    private Integer num;

    private BigDecimal amount;

    private Boolean pay;

    private Long userId;

}
