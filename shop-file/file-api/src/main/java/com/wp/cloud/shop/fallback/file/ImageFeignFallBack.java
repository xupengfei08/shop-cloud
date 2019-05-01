package com.wp.cloud.shop.fallback.file;

import com.wp.cloud.shop.dto.file.ImageDto;
import com.wp.cloud.shop.dto.file.ImagePathDto;
import com.wp.cloud.shop.fegin.file.ImageFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Title: shop-cloud--UserFeignFallBack
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-01-30 13:58
 */
@Component
@Slf4j
public class ImageFeignFallBack implements FallbackFactory<ImageFeign> {

    @Override
    public ImageFeign create(Throwable throwable) {
        return new ImageFeign() {
            @Override
            public ImageDto getImage(ImagePathDto path) {
                log.error("请求熔断降级：", throwable);
                return null;
            }

            @Override
            public ImagePathDto saveImage(ImageDto image) {
                log.error("请求熔断降级：", throwable);
                return null;
            }

            @Override
            public ImagePathDto saveImage(MultipartFile image) {
                log.error("请求熔断降级：", throwable);
                return null;
            }

            @Override
            public ImagePathDto saveImageToMongo(MultipartFile image) {
                log.error("请求熔断降级：", throwable);
                return null;
            }

            @Override
            public void deleteImage(ImagePathDto imagePath) {
                log.error("请求熔断降级：", throwable);
            }
        };
    }
}
