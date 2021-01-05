package com.hgnu.study.service;

import com.hgnu.study.entity.oss.FileUploadResult;
import com.hgnu.study.entity.oss.OssCallbackResult;
import com.hgnu.study.entity.oss.OssPolicyResult;
import com.aliyun.oss.model.OSSObjectSummary;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * oss文件上传接口
 */

public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);

    /**
     * 文件上传
     * @param file
     * @return
     */
    FileUploadResult upload(MultipartFile file);

    /**
     * 查看文件列表
     * @return
     */
    List<OSSObjectSummary> list(String bucketName);

    /**
     * 删除文件
     * @param objectName
     * @return
     */
    FileUploadResult delete(String objectName);

    /**
     * 下载文件
     * @param os
     * @param objectName
     * @throws IOException
     */
    void exportOssFile(OutputStream os, String objectName) throws IOException;
}
