package com.wp.cloud.shop.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.wp.cloud.shop.common.utils.MD5Util;
import com.wp.cloud.shop.common.utils.OkHttpUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.config.QQCampusConfig
 * @Description: 签名生成
 * @Author suanmilk
 * @CreateTime: 2019-03-28 22:00
 */
@Component
@ConfigurationProperties(
        prefix = "qq.campus"
)
public class QQCampusConfig {

    private String objectid = "T58TChZcHvgdone";
    private int objType = 2;
    private String devCode = "IKKPLxQ7KGMyzaf6";
    private int devType = 3;
    private String key = "5431b2d3db6468351735256f852de040";
    private int keyId = 1173;

    /**
     * sign 签名 （参数名按ASCII码从小到大排序（字典序）+key+MD5+转大写签名）
     *
     * @param params
     * @return
     */
    public SortedMap<String, String> encodeSign(SortedMap<String, String> params) {
        // 公共参数
        long timestamp = Calendar.getInstance().getTimeInMillis();
        params.put("devCode", devCode);
        params.put("devType", String.valueOf(devType));
        params.put("keyId", String.valueOf(keyId));
        params.put("timestamp", String.valueOf(timestamp));
        params.put("objectid", objectid);
        params.put("objType", String.valueOf(objType));

        Set<Map.Entry<String, String>> entries = params.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        List<String> values = Lists.newArrayList();

        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            String k = String.valueOf(entry.getKey());
            String v = String.valueOf(entry.getValue());
            if (StringUtils.isNotEmpty(v) && entry.getValue() != null && !"sign".equals(k) && !"key".equals(k)) {
                values.add(k + "=" + v);
            }
        }
        values.add("key=" + key);
        String stringSignTemp = StringUtils.join(values, "&");

        // sign 签名
        String sign = MD5Util.encodeByMD5(stringSignTemp);
//        System.out.println("签名sign:" + sign);
        params.put("sign", sign);
        return params;
    }

    /**
     * 通过用户id查询单个用户信息(学生、老师、家长、校友、退休教师）
     */
    public void getUserInfo() {
        System.out.println("********** 获取单个用户信息 **********");
        SortedMap<String, String> params = new TreeMap<>();
        params.put("userid", "HxKp776PKYIdone");
        params = encodeSign(params);
        String response = OkHttpUtil.get("https://open.campus.qq.com/api/open/getUserInfo", params);
        JSONObject jsonObject = JSON.parseObject(response);

        System.out.println(JSON.toJSONString(jsonObject, true));
    }

    /**
     * 通过学校id查询学校信息
     */
    public void getObjectInfo() {
        System.out.println("********** 获取学校信息 **********");
        SortedMap<String, String> params = new TreeMap<>();
        params.put("userid", "L3G3ifG8eOodone");
        params = encodeSign(params);
        String response = OkHttpUtil.get("https://open.campus.qq.com/api/open/getObjectInfo", params);
        JSONObject jsonObject = JSON.parseObject(response);

        System.out.println(JSON.toJSONString(jsonObject, true));
    }

    /**
     * 通过用户id验证用户是否为学校管理员
     */
    public void getAdminInfo() {
        System.out.println("********** 验证管理员身份 **********");
        SortedMap<String, String> params = new TreeMap<>();
        params.put("userid", "pR02Bn79WNQdone");
        params = encodeSign(params);
        String response = OkHttpUtil.get("https://open.campus.qq.com/api/open/getAdminInfo", params);
        JSONObject jsonObject = JSON.parseObject(response);

        System.out.println(JSON.toJSONString(jsonObject, true));
    }

    /**
     * 获取老师、学生、退休老师、校友等用户列表数据
     */
    public void searchUser() {
        System.out.println("********** 获取/搜索用户列表 **********");
        SortedMap<String, String> params = new TreeMap<>();
        // 用户类型。（1-学生；2-老师；4-校友；5-退休老师；6-其他）
        params.put("usertype", "2");
        params.put("departid", "mYaFpKIY6YQdone");
        // 组织架构级别（对于大学：2-学部，3-学院，4-系，5-专业，6-班级；对于中小学：2-学部，5-年级，6-班级）
        params.put("level", "2");
        params = encodeSign(params);
        String response = OkHttpUtil.get("https://open.campus.qq.com/api/common/searchUser", params);
        JSONObject jsonObject = JSON.parseObject(response);

        System.out.println(JSON.toJSONString(jsonObject, true));
    }

    /**
     * 根据学校id获取学校管理员列表
     * {
     * "name":"创建者",
     * "userid":"TLktDFHE6HQdone"
     * },
     * {
     * "name":"陈凉",
     * "userid":"3GILjk2tjuneu0done"
     * },
     * {
     * "name":"陈刚",
     * "userid":"L3G3ifG8eOodone"
     * },
     * {
     * "name":"曹蔚萍",
     * "userid":"pR02Bn79WNQdone"
     * },
     * {
     * "name":"严适",
     * "userid":"xkUt1HHUAmsdone"
     * }
     */
    public void getAdminList() {
        System.out.println("********** 获取学校管理员列表 **********");
        SortedMap<String, String> params = new TreeMap<>();
        params.put("userid", "DjuneaqZGVsnvwdone");
        params = encodeSign(params);
        String response = OkHttpUtil.get("https://open.campus.qq.com/api/open/getAdminList", params);
        JSONObject jsonObject = JSON.parseObject(response);

        System.out.println(JSON.toJSONString(jsonObject, true));
    }

    /**
     * 根据班级id获取班级管理员列表
     */
    public void getDepartAdminList() {
        System.out.println("********** 获取班级管理员列表 **********");
        SortedMap<String, String> params = new TreeMap<>();
        params.put("departid", "UCQSmDyOvbodone");
        params = encodeSign(params);
        String response = OkHttpUtil.get("https://open.campus.qq.com/api/schAdminInfo/getDepartAdminList", params);
        JSONObject jsonObject = JSON.parseObject(response);

        System.out.println(JSON.toJSONString(jsonObject, true));
    }

    /**
     * 根据老师ID获取所管理的班级列表
     */
    public void getManageDepartList() {
        System.out.println("********** 获取老师管理的班级列表 **********");
        SortedMap<String, String> params = new TreeMap<>();
        params.put("userid", "HxKp776PKYIdone");
        params = encodeSign(params);
        String response = OkHttpUtil.get("https://open.campus.qq.com/api/open/getManageDepartList", params);
        JSONObject jsonObject = JSON.parseObject(response);

        System.out.println(JSON.toJSONString(jsonObject, true));
    }

    /**
     * 根据组织架构id获取组织架构信息
     */
    public void getDepartInfoById() {
        System.out.println("********** 获取组织架构信息 **********");
        SortedMap<String, String> params = new TreeMap<>();
        // 组织架构id
        params.put("departid", "FpfDPRaPmuodone");
        params = encodeSign(params);
        String response = OkHttpUtil.get("https://open.campus.qq.com/api/open/getDepartInfoById", params);
        JSONObject jsonObject = JSON.parseObject(response);

        System.out.println(JSON.toJSONString(jsonObject, true));
    }

    /**
     * 根据用户类型获取组织架构列表
     */
    public void getDepartmentInfoList() {
        System.out.println("********** 获取组织架构列表 **********");
        SortedMap<String, String> params = new TreeMap<>();
        // 角色类型（1-学生；2-教师；3-家长；4-校友；5-退休教师；6-其他 )
        params.put("usertype", "2");
        params = encodeSign(params);
        String response = OkHttpUtil.get("https://open.campus.qq.com/api/open/getDepartmentInfoList", params);
        JSONObject jsonObject = JSON.parseObject(response);

        System.out.println(JSON.toJSONString(jsonObject, true));
    }

    /**
     * 通过学生/家长id查询其家长/孩子信息
     */
    public void getParentRelation() {
        System.out.println("********** 获取学生-家长关系 **********");
        SortedMap<String, String> params = new TreeMap<>();
        params.put("userid", "426QjDcRSv8done");
        // 角色类型（1-学生；3-家长 )
        params.put("usertype", "1");
        params = encodeSign(params);
        String response = OkHttpUtil.get("https://open.campus.qq.com/api/open/getParentRelation", params);
        JSONObject jsonObject = JSON.parseObject(response);

        System.out.println(JSON.toJSONString(jsonObject, true));
    }

    /**
     * 通过学生组织架构id查询家长列表
     */
    public void parentList() {
        System.out.println("********** 获取家长列表 **********");
        SortedMap<String, String> params = new TreeMap<>();
        params.put("departid", "UCQSmDyOvbodone");
        params = encodeSign(params);
        String response = OkHttpUtil.get("https://open.campus.qq.com/api/userInfo/parentList", params);
        JSONObject jsonObject = JSON.parseObject(response);

        System.out.println(JSON.toJSONString(jsonObject, true));
    }

    public static void main(String[] args) {
        QQCampusConfig config = new QQCampusConfig();
//        config.getUserInfo();
//        config.getObjectInfo();
//        config.searchUser();
//        config.getAdminInfo();
//        config.getAdminList();
//        config.getDepartAdminList();
//        config.getManageDepartList();
//        config.getDepartInfoById();
//        config.getDepartmentInfoList();
        config.getParentRelation();
//        config.parentList();

        // 学生组织架构和学生信息每天更新一次

        // 教师组织架构和教师信息每天更新一次

    }

}
