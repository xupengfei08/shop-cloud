package com.wp.cloud.shop.fallback.user;

import com.wp.cloud.shop.dto.user.UserDto;
import com.wp.cloud.shop.fegin.user.UserFeign;
import com.wp.cloud.shop.vo.user.UserVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title: shop-cloud--UserFeignFallBack
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-01-30 13:58
 */
@Component
@Slf4j
public class UserFeignFallBack implements FallbackFactory<UserFeign> {

    @Override
    public UserFeign create(Throwable throwable) {
        return new UserFeign() {

            @Override
            public List<UserDto> getUsers(UserVo userVo) {
                log.error("请求熔断降级：", throwable);
                return null;
            }

            @Override
            public UserDto getUser(Long id) {
                log.error("请求熔断降级：", throwable);
                return null;
            }
        };
    }
}
