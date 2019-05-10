package com.wp.cloud.shop.model;

import lombok.Data;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.model.UploadInfo
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-05-09 14:07
 */
@Data
public class UploadInfo {
    private String fileName;
    private String storePath;
    private String oldMd5;
    private String newMd5;
    private Boolean error;
}
