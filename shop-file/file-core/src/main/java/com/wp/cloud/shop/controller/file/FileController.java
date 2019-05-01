package com.wp.cloud.shop.controller.file;

import com.facemeng.cloud.school.common.utils.Base64Util;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.wp.cloud.shop.dto.file.ImageDto;
import com.wp.cloud.shop.dto.file.ImagePathDto;
import com.wp.cloud.shop.fegin.file.ImageFeign;
import com.wp.cloud.shop.service.FastDFSClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

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
        try {
            String path = fdfsClient.uploadFile(file);
            return new ImagePathDto(path);
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
}
