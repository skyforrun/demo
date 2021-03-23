package com.hgnu.study.oss.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: zj
 * @time: 2020/12/21 13:54
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadResult {

    @ApiModelProperty("文件唯一标识")
    private String uid;

    @ApiModelProperty("文件名")
    private String name;

    @ApiModelProperty("状态有：uploading done error removed")
    private String status;

    @ApiModelProperty("服务端响应内容，如：'{\"status\": \"success\"}'")
    private String response;
}
