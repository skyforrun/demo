package cn.com.kxyt.controller.oss;

import cn.com.kxyt.core.Result;
import cn.com.kxyt.entity.oss.FileUploadResult;
import cn.com.kxyt.entity.oss.OssCallbackResult;
import cn.com.kxyt.entity.oss.OssPolicyResult;
import cn.com.kxyt.exception.GlobalExceptionHandler;
import cn.com.kxyt.service.OssService;
import com.aliyun.oss.model.OSSObjectSummary;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @description: oss对象存储controller
 *          1、Web前端请求应用服务器，获取上传所需参数（如OSS的accessKeyId、policy、callback等参数）
 *          2、应用服务器返回相关参数
 *          3、Web前端直接向OSS服务发起上传文件请求
 *          4、等上传完成后OSS服务会回调应用服务器的回调接口
 *          5、应用服务器返回响应给OSS服务
 *          6、OSS服务将应用服务器回调接口的内容返回给Web前端
 * @author: zj
 * @time: 2020/12/21 11:20
 */

@RestController
@RequestMapping("/oss")
@Api(tags = "OssController", value = "Oss管理")
public class OssController {

    @Autowired
    OssService ossService;

    @ApiOperation(value = "oss上传签名生成")
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    public Result<OssPolicyResult> policy() {
        OssPolicyResult result = ossService.policy();
        return Result.success(result);
    }

    @ApiOperation(value = "oss上传成功回调")
    @RequestMapping(value = "callback", method = RequestMethod.POST)
    @ResponseBody
    public Result<OssCallbackResult> callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return Result.success(ossCallbackResult);
    }

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file){
        FileUploadResult upload = ossService.upload(file);
        return Result.success(upload);
    }

    @ApiOperation("bucket文件列表")
    @PostMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name="bucketName",value="bucket文件名称",defaultValue = "skyforrun-oss")
    })
    public Result bucketList(String bucketName){
        List<OSSObjectSummary> list = ossService.list(bucketName);
        return Result.success(list);
    }

    @ApiOperation("删除文件")
    @PostMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name="key",value="删除文件的key")
    })
    public Result deleteFile(String key){
        FileUploadResult delete = ossService.delete(key);
        return Result.success(delete);
    }

    @ApiOperation("下载文件")
    @PostMapping("/download")
    @ApiImplicitParams({
            @ApiImplicitParam(name="key",value="下载文件的key")
    })
    public Result downloadFile(OutputStream os,String key) throws IOException {
        try {
            ossService.exportOssFile(os,key);
        }catch (Exception e){
            return GlobalExceptionHandler.ExceptionHandler(e,"下载失败");
        }
        return Result.success();
    }
}
