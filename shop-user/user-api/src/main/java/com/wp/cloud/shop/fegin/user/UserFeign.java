package com.wp.cloud.shop.fegin.user;

import com.wp.cloud.shop.dto.user.UserDto;
import com.wp.cloud.shop.fallback.user.UserFeignFallBack;
import com.wp.cloud.shop.vo.user.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Title: shop-cloud--com.wp.shop.user.fegin.UserApi
 * @Description: 用户信息相关接口
 * @Author suanmilk
 * @CreateTime: 2019-01-30 12:46
 */
@FeignClient(name = "shop-user-service", fallbackFactory = UserFeignFallBack.class)
@RequestMapping("/user")
public interface UserFeign {
    /**
     * 查询所有用户信息
     *
     * @return
     */
    @GetMapping
    List<UserDto> getUsers(@ModelAttribute UserVo userVo);

    /**
     * 根据ID查询用户信息
     *
     * @return
     */
    @GetMapping("/{id}")
    UserDto getUser(@PathVariable("id") Long id);
}
