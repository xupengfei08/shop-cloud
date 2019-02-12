package com.wp.cloud.shop.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.order.model.dto.OrderDto
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-01-31 10:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;

    private String goodsName;

    private BigDecimal price;

    private Integer num;

    private BigDecimal amount;

    private Boolean pay;

    private Long userId;
}
