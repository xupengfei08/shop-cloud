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
@ApiModel(description = "图片信息")
public class ImageDto {

    @ApiModelProperty(value = "大小", example = "102400")
    private Long size;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "格式")
    private String format;

    @ApiModelProperty(value = "宽度", example = "600")
    private Integer width;

    @ApiModelProperty(value = "高度", example = "800")
    private Integer height;

    @ApiModelProperty(value = "base64信息")
    private String base64;
}
