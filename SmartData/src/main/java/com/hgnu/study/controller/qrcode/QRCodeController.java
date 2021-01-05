package com.hgnu.study.controller.qrcode;

import com.hgnu.study.entity.StandAddress;
import com.hgnu.study.mapper.StandAddressMapper;
import com.hgnu.study.util.QRCodeUtil;
import com.google.zxing.Result;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zj
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/1316:59
 */
@RequestMapping("/code")
@RestController
@Api(value = "二维码生成器接口",tags = "传入id生成二维码")
public class QRCodeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StandAddressMapper addressMapper;

    /**
     * 生成二维码
     */
    @GetMapping("/{id}")
    @ApiOperation(value="生成二维码",notes="id必须为数据库表唯一的id，主键最好")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="表的唯一id",required=true)
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public com.hgnu.study.core.Result productcode(@PathVariable String id) {
        logger.info("开始生成二维码,id为:"+id);
        QRCodeUtil.zxingCodeCreate("http://192.168.1.12/code/test/"+id, "./public/qrcode/"+id,500,null);
        logger.info("生成二维码成功");
        StandAddress address = new StandAddress();
        address.setImageSrc("http://192.168.1.12/"+id+".jpg");
        return com.hgnu.study.core.Result.success(address);
    }


    /**
     * 解析二维码
     */
    @GetMapping("/test/{id}")
    @ApiOperation(value="解析二维码",notes="id必须为数据库表唯一的id，主键最好")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="表的唯一id",required=true)
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public com.hgnu.study.core.Result analysiscode(@PathVariable String id) {
        logger.info("开始解析二维码，ID为:"+id);
        Result result = QRCodeUtil.zxingCodeAnalyze("./public/qrcode/"+id+".jpg");
        //取出地址栏的唯一参数ID
        String uid = result.toString().substring(result.toString().lastIndexOf("/")+1);
        StandAddress standAddress = addressMapper.selectStandAddressById(Integer.valueOf(uid));
        logger.info("ID为"+id+"解析的结果为："+standAddress);
        return com.hgnu.study.core.Result.success(standAddress);
    }

}
