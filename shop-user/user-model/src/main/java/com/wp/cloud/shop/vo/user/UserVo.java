package com.wp.cloud.shop.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: shop-cloud--UserVo
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-01-30 13:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户信息查询请求数据")
public class UserVo {

    @ApiModelProperty(value = "姓名", notes = "支持模糊查询")
    private String namelike;

    @ApiModelProperty(value = "是否为VIP用户")
    private Boolean vip;
}
