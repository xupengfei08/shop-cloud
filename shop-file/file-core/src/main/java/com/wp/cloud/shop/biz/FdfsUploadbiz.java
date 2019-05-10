package com.wp.cloud.shop.biz;

import com.wp.cloud.shop.model.UploadInfo;
import com.wp.cloud.shop.service.FastDFSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.biz.FdfsUploadbiz
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-05-09 11:03
 */
@Service
@Slf4j
public class FdfsUploadbiz {

    @Autowired
    private FastDFSClient fdfsClient;

    @Async
    public void uploadFile(File file, UploadInfo uploadInfo) {
        // 上传图片
        log.info("上传图片开始");
        try {
            String storePath = fdfsClient.uploadFile(file);
            uploadInfo.setStorePath(storePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            log.info("上传图片结束");
        }
    }

    @Async
    public void uploadFile(InputStream inputStream, long length, String fileName) {
        // 上传图片
        fdfsClient.uploadFile(inputStream, length, fileName);
    }
}
