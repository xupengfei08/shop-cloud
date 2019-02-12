package com.wp.cloud.shop.vo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.order.model.vo.OrderVo
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-01-31 10:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVo {

    private Long id;

    private BigDecimal amount;

    private Boolean pay;

    private Long userId;
}
