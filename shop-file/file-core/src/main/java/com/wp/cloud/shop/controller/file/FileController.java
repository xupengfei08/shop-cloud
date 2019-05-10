package com.wp.cloud.shop.controller.file;

import com.alibaba.fastjson.JSON;
import com.facemeng.cloud.school.common.utils.Base64Util;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.wp.cloud.shop.biz.FdfsUploadbiz;
import com.wp.cloud.shop.dto.file.ImageDto;
import com.wp.cloud.shop.dto.file.ImagePathDto;
import com.wp.cloud.shop.fegin.file.ImageFeign;
import com.wp.cloud.shop.model.UploadInfo;
import com.wp.cloud.shop.service.FastDFSClient;
import com.wp.cloud.shop.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @Title: shop-cloud--FileController
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-03-27 13:06
 */
@RestController
@Slf4j
@Api(description = "文件存储管理")
public class FileController implements ImageFeign {

    @Autowired
    private FastDFSClient fdfsClient;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private FdfsUploadbiz fdfsUploadbiz;

    @Override
    @ApiOperation(value = "根据图片路径获取图片信息", notes = "根据图片路径获取图片信息")
    public ImageDto getImage(@ModelAttribute ImagePathDto imagePath) {
        byte[] fileByte = fdfsClient.download(imagePath.getPath());
        String base64 = Base64Util.encodeBase64(fileByte);
        ImageDto imageDto = new ImageDto();
        imageDto.setBase64(base64);
        return imageDto;
    }

    @Override
    @ApiOperation(value = "上传图片", notes = "上传图片")
    public ImagePathDto saveImage(@RequestBody ImageDto image) {
        String fileName = StringUtils.isEmpty(image.getName()) ? String.valueOf(Calendar.getInstance().getTimeInMillis()) : image.getName();
        String path = fdfsClient.uploadFile(image.getBase64(), fileName + ".jpg");
        return new ImagePathDto(path);
    }

    @Override
    @ApiOperation(value = "上传图片", notes = "上传图片")
    public ImagePathDto saveImage(@RequestParam @ApiParam(value = "图片文件", required = true) MultipartFile file) {
        String fileName = String.valueOf(Calendar.getInstance().getTimeInMillis());

        try {
            fdfsUploadbiz.uploadFile(file.getInputStream(), file.getSize(), fileName+ ".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @ApiOperation(value = "上传图片", notes = "上传图片到MongoDB")
    public ImagePathDto saveImageToMongo(@RequestParam @ApiParam(value = "图片文件", required = true) MultipartFile image) {
        DBObject metaData = new BasicDBObject();
        metaData.put("createdDate", new Date());
        // 获得提交的文件名
        String fileName = image.getOriginalFilename();
        // 获得文件类型
        String contentType = image.getContentType();
        try {
            InputStream inputStream = image.getInputStream();
            ObjectId objectId = gridFsTemplate.store(inputStream, fileName, contentType, metaData);
            return new ImagePathDto(objectId.toString());
        } catch (IOException e) {
            throw new RuntimeException("System Exception while handling request");
        }
    }

    @Override
    @ApiOperation(value = "删除图片", notes = "删除图片")
    public void deleteImage(@RequestBody ImagePathDto imagePath) {
        fdfsClient.deleteFile(imagePath.getPath());
    }

    @PostMapping("/run")
    @ApiOperation(value = "上传文件任务", notes = "上传文件任务")
    public List<UploadInfo> runTask(@RequestParam("path") @ApiParam(value = "文件目录", required = true) String path,
                                    @RequestParam("limit") @ApiParam(value = "上传文件数", example = "50") Integer limit,
                                    @RequestParam("sleep") @ApiParam(value = "等待结果秒数", example = "10") Integer sleep) {
        File filePath = new File(path);
        if (filePath.exists() && filePath.isDirectory()) {
            File[] files = filePath.listFiles();
            if (files != null && files.length > 0) {
                int index = 0;
                List<UploadInfo> uploadInfoList = new ArrayList<>();
                for (File file : files) {
                    if (limit != null && index == limit)
                        break;
                    // 计算旧的文件MD5值
                    UploadInfo uploadInfo = new UploadInfo();
                    uploadInfo.setFileName(file.getName());
                    uploadInfo.setOldMd5(MD5Util.fileMD5(file));
                    fdfsUploadbiz.uploadFile(file, uploadInfo);
                    uploadInfoList.add(uploadInfo);
                    index++;
                }
                if (sleep == null || sleep <= 0)
                    sleep = 10;
                try {
                    Thread.sleep(sleep * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 计算上传后的文件MD5
                uploadInfoList.stream().forEach(uploadInfo -> {
                    if (uploadInfo.getStorePath() != null) {
                        byte[] data = fdfsClient.download(uploadInfo.getStorePath());
                        uploadInfo.setNewMd5(MD5Util.byteMD5(data));
                        if (uploadInfo.getOldMd5().equals(uploadInfo.getNewMd5())) {
                            uploadInfo.setError(false);
                        } else {
                            uploadInfo.setError(true);
                        }
                    }
                });
                log.info(JSON.toJSONString(uploadInfoList));
                Iterator<UploadInfo> iterable = uploadInfoList.iterator();
                while (iterable.hasNext()) {
                    UploadInfo info = iterable.next();
                    if (!info.getError())
                        iterable.remove();
                }
                log.info(JSON.toJSONString(uploadInfoList, true));
                return uploadInfoList;
            }
        } else {
            log.error("指定路径异常");
        }
        return null;
    }
}
