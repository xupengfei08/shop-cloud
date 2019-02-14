package com.wp.cloud.shop.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "订单查询请求数据")
public class OrderDto {

    @ApiModelProperty(value = "订单ID",example = "10")
    private Long id;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "单价",example = "100")
    private BigDecimal price;

    @ApiModelProperty(value = "数量",example = "10")
    private Integer num;

    @ApiModelProperty(value = "总金额",example = "1000")
    private BigDecimal amount;

    @ApiModelProperty(value = "是否支付")
    private Boolean pay;

    @ApiModelProperty(value = "用户ID",example = "10")
    private Long userId;
}
