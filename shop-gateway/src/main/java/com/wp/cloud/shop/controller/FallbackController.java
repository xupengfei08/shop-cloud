package com.wp.cloud.shop.controller;

import com.wp.cloud.shop.common.enumerate.HttpStatus;
import com.wp.cloud.shop.common.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.controller.FallbackController
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-04-11 13:03
 */
@RestController
public class FallbackController {

    private static HttpStatus STATUS = HttpStatus.GATEWAY_TIMEOUT;

    @GetMapping("/fallback")
    public Result fallback() {
        return Result.builder().error(STATUS.getReasonPhrase()).status(STATUS.value()).build();
    }

}
