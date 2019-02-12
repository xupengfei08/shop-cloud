package com.wp.cloud.shop.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: cloud-test--com.wpline.cloud.zuul.filter.RequestLogFilter
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-01-26 21:26
 */
@Component
@Slf4j
public class RequestLogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        log.info("[RequestLog]请求地址：{}", request.getRequestURI());
        return null;
    }
}
