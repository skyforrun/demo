package com.hgnu.study.controller.qrcode;

import com.google.zxing.Result;
import com.hgnu.study.elasticsearch.entity.City;
import com.hgnu.study.elasticsearch.mapper.CityMapper;
import com.hgnu.study.exception.TipException;
import com.hgnu.study.qrcode.QRCodeUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * @author zj
 * @Description:
 * @date 2020/11/13 16:59
 */
@RequestMapping("/code")
@RestController
@Slf4j
@Api(value = "二维码生成器接口",tags = "传入id生成二维码")
public class QRCodeController {

    @Autowired
    CityMapper cityMapper;

    /**
     * 生成二维码
     */
    @PostMapping("/{id}")
    @ApiOperation(value="传入id生成二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="city主键",defaultValue = "1")
    })
    public void productcode(@PathVariable Integer id) {
        log.info("开始生成二维码,id为:"+id);
        QRCodeUtil.zxingCodeCreate("http://192.168.124.8/code/test/"+id, "./qrcode/"+id,500,null);
        log.info("生成二维码成功");
    }


    /**
     * 解析二维码
     */
    @GetMapping("/test/{id}")
    @ApiOperation(value="解析二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="city主键",defaultValue = "1")
    })
    @Transactional(value = "dataSourceTransactionManager3",rollbackFor = TipException.class)
    public City analyCode(@PathVariable String id) {
        log.info("开始解析二维码，ID为:"+id);
        Result result = QRCodeUtil.zxingCodeAnalyze("./qrcode/"+id+".jpg");
        String uid = result.toString().substring(result.toString().lastIndexOf("/")+1);
        City city = cityMapper.selectById(uid);
        log.info("ID为"+id+"解析的结果为："+city);
        return city;
    }
}
