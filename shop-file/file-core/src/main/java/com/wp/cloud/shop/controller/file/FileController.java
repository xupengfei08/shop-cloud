package com.wp.cloud.shop.controller.file;

import com.wp.cloud.shop.dto.file.ImageDto;
import com.wp.cloud.shop.dto.file.ImagePathDto;
import com.wp.cloud.shop.fegin.file.ImageFeign;
import com.wp.cloud.shop.service.FastDFSClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @Override
    @ApiOperation(value = "根据图片路径获取图片信息", notes = "根据图片路径获取图片信息")
    public ImageDto getImage(@ModelAttribute ImagePathDto path) {
        return null;
    }

    @Override
    @ApiOperation(value = "上传图片", notes = "上传图片")
    public ImagePathDto saveImage(@RequestBody ImageDto image) {
        return null;
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
}
