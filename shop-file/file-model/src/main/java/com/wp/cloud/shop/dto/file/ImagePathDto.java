package com.wp.cloud.shop.dto.file;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: com.wp.cloud.shop.dto.file.ImageDto
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-03-24 20:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "图片路径信息")
public class ImagePathDto {

    @ApiModelProperty(value = "路径")
    private String path;
}
