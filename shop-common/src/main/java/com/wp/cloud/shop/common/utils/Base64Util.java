package com.wp.cloud.shop.common.utils;

import org.springframework.util.StringUtils;

import java.util.Base64;

/**
 * Title: cloud-server--com.facemeng.compare.common.utils.Base64Util
 * Description: Base64编解码工具类
 * Copyright: Copyright (c) 2017/12
 * Create DateTime: 2017/12/28 下午7:55
 *
 * @author suanmilk
 * @version v2
 */
public class Base64Util {

    /**
     * 使用Base64进行编码
     *
     * @param input
     * @return
     * @throws Exception
     */
    public static String encodeBase64(String input) throws Exception {
        if (StringUtils.isEmpty(input))
            return null;
        return encodeBase64(input.getBytes("utf-8"));
    }

    /**
     * 使用Base64进行编码
     *
     * @param input
     * @return
     */
    public static String encodeBase64(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }

    /***
     * 使用Base64解码
     */
    public static byte[] decodeBase64(String input) {
        return Base64.getDecoder().decode(input);
    }

}
