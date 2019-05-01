package com.wp.cloud.shop.service;

import com.facemeng.cloud.school.common.utils.Base64Util;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import com.github.tobato.fastdfs.exception.FdfsServerException;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @Title: shop-cloud--com.wp.cloud.shop.service.FastDFSClient
 * @Description: FastDFS文件存储接口封装
 * @Author suanmilk
 * @CreateTime: 2019-03-27 23:01
 */
@Component
@Slf4j
public class FastDFSClient {

    @Autowired
    private FastFileStorageClient storageClient;

    /**
     * 上传文件
     *
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        log.info("FastDFS 存储文件地址 = {} ", storePath.getFullPath());
        return getResAccessUrl(storePath);
    }

    /**
     * 上传文件
     *
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    public String uploadFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        StorePath storePath = storageClient.uploadFile(inputStream, file.length(), FilenameUtils.getExtension(file.getName()), null);
        log.info("FastDFS 存储文件地址 = {} ", storePath.getFullPath());
        return getResAccessUrl(storePath);
    }

    /**
     * 上传文件
     *
     * @param fastImageFile
     * @return 文件访问地址
     */
    public String uploadFile(FastImageFile fastImageFile) {
        StorePath storePath = storageClient.uploadImage(fastImageFile);
        log.info("FastDFS 存储文件地址 = {} ", storePath.getFullPath());
        return getResAccessUrl(storePath);
    }


    /**
     * 将Base64文件存储
     *
     * @param base64
     * @param fileName
     * @return
     */
    public String uploadFile(String base64, String fileName) {
        byte[] byteArray = Base64Util.decodeBase64(base64);
        long contentLength = (long) byteArray.length;
        InputStream inputStream = new ByteArrayInputStream(byteArray);
        log.info("需要存储的文件的信息 -> {}, {}", fileName, contentLength);
        StorePath storePath = storageClient.uploadFile(inputStream, contentLength, FilenameUtils.getExtension(fileName), null);
        log.info("FastDFS 存储文件地址 -> {} ", storePath.getFullPath());
        return getResAccessUrl(storePath);
    }

    // 封装图片完整URL地址
    private String getResAccessUrl(StorePath storePath) {
//        String fileUrl = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
//        return fileUrl;
        return storePath.getFullPath();
    }

    /**
     * 下载文件
     *
     * @param fileUrl 文件url
     * @return
     */
    public byte[] download(String fileUrl) {
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        byte[] bytes = storageClient.downloadFile(group, path, new DownloadByteArray());
        return bytes;
    }

    /**
     * 删除文件
     *
     * @param fileUrl 文件访问地址
     * @return
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
//            FileInfo fileInfo = storageClient.queryFileInfo(storePath.getGroup(), storePath.getPath());
//            if (fileInfo != null) {
//                log.info("删除FastDFS文件 = {}", storePath.getFullPath());
//                storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
//            }

            log.info("删除FastDFS文件 = {}", storePath.getFullPath());
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            log.error(e.getMessage());
        } catch (FdfsServerException e) {
            log.error(e.getMessage());
        }
    }

}
