package com.wp.cloud.shop.fegin.file;

import com.wp.cloud.shop.dto.file.ImageDto;
import com.wp.cloud.shop.dto.file.ImagePathDto;
import com.wp.cloud.shop.fallback.file.ImageFeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Title: shop-cloud--com.wp.shop.user.fegin.UserApi
 * @Description: 用户信息相关接口
 * @Author suanmilk
 * @CreateTime: 2019-01-30 12:46
 */
@FeignClient(name = "shop-file-service", fallbackFactory = ImageFeignFallBack.class)
@RequestMapping("/image")
public interface ImageFeign {

    /**
     * 根据图片路径获取图片信息
     *
     * @param path
     * @return
     */
    @GetMapping
    ImageDto getImage(@ModelAttribute ImagePathDto path);

    /**
     * 上传图片
     *
     * @param image
     * @return
     */
    @PostMapping
    ImagePathDto saveImage(@RequestBody ImageDto image);


    /**
     * 上传图片
     *
     * @param image
     * @return
     */
    @PostMapping("/upload")
    ImagePathDto saveImage(@RequestParam("file") MultipartFile image);
}
