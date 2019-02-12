package com.wp.cloud.shop.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.Map;

/**
 * @Title: cloud-test--com.wpline.cloud.zuul.filter.ExceptionFilter
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-01-27 12:59
 */
@Component
@Slf4j
public class ExceptionFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_ERROR_FILTER_ORDER + 10;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        // 可以在此统一处理执行之前的异常
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();
        try {
            Map<String, Object> result;
            // 输出异常
            throwable.printStackTrace();
            if (throwable instanceof ZuulException) {
                ZuulException exception = (ZuulException) throwable.getCause();
                // 正常response
                ctx.setResponseStatusCode(exception.nStatusCode);
//                ctx.getResponse().setContentType("application/json;charset=UTF-8");
                ctx.setResponseBody(exception.errorCause);
            } else {
            }
            // 避免SendErrorFilter处理，直接进入post类型过滤器处理
//            ctx.setThrowable(null);
            ctx.remove("throwable");
        } catch (Exception var5) {
            ReflectionUtils.rethrowRuntimeException(var5);
        }
        log.error(throwable.getMessage());
        return null;
    }
}
