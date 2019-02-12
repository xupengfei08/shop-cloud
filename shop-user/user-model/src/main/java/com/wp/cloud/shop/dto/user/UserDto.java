package com.wp.cloud.shop.dto.user;

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
public class UserDto {
    private Long id;
    private String username;
    private String name;
    private Integer age;
    private Boolean vip;
    private BigDecimal balance;
}
