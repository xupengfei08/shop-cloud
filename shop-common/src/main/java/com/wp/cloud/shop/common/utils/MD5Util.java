package com.wp.cloud.shop.common.utils;

import java.security.MessageDigest;

/**
 * Title: cloud-server--com.facemeng.sun.common.utils.MD5Util
 * Description: MD5工具类
 * Copyright: Copyright (c) 2018/01
 * Create DateTime: 2018/1/4 下午3:03
 *
 * @author suanmilk
 * @version v2
 */
public class MD5Util {
    //公盐
    private static final String PUBLIC_SALT = "SUN";
    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 用户密码加密，盐值为 ：私盐+公盐
     *
     * @param password 密码
     * @param salt     私盐
     * @return MD5加密字符串
     */
    public static String encryptPassword(String password, String salt) {
        return encodeByMD5(PUBLIC_SALT + password + salt);
    }

    /**
     * md5加密算法
     *
     * @param originString
     * @return
     */
    public static String encodeByMD5(String originString) {
        if (originString != null) {
            try {
                //创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md.digest(originString.getBytes());
                //将得到的字节数组变成字符串返回
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 转换字节数组为十六进制字符串
     *
     * @param b 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 将一个字节转化成十六进制形式的字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.encryptPassword("admin", "1524474007794"));
    }

}
