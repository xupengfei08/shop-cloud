package com.wp.cloud.shop.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Title: cloud-test--com.wpline.cloud.buy.model.User
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-01-24 20:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户请求数据")
public class UserDto {

    @ApiModelProperty(value = "用户ID",example = "1")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄",example = "25")
    private Integer age;

    @ApiModelProperty(value = "是否为VIP用户")
    private Boolean vip;

    @ApiModelProperty(value = "余额",example = "500")
    private BigDecimal balance;
}
