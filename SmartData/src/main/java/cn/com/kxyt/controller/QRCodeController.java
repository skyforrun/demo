package cn.com.kxyt.controller;

import cn.com.kxyt.entity.StandAddress;
import cn.com.kxyt.mapper.StandAddressMapper;
import cn.com.kxyt.mapper2.UserMapper;
import cn.com.kxyt.util.QRCodeUtil;
import com.google.zxing.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zj
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/1316:59
 */
@RequestMapping("/code")
@RestController
@CrossOrigin
public class QRCodeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StandAddressMapper addressMapper;

    /**
     * 生成二维码
     */
    @GetMapping("/{id}")
    public String productcode(@PathVariable String id) {
        logger.info("开始生成二维码,id为:"+id);
        QRCodeUtil.zxingCodeCreate("http://192.168.1.12/code/test/"+id, "./qrcode/"+id,500,null);
        logger.info("生成二维码成功");
        return "http://192.168.1.12/"+id+".jpg";
    }


    /**
     * 解析二维码
     */
    @GetMapping("/test/{id}")
    public StandAddress analysiscode(@PathVariable String id) {
        logger.info("开始解析二维码，ID为:"+id);
        Result result = QRCodeUtil.zxingCodeAnalyze("./qrcode/"+id+".jpg");
        //取出地址栏的唯一参数ID
        String uid = result.toString().substring(result.toString().lastIndexOf("/")+1);
        StandAddress standAddress = addressMapper.selectStandAddressById(Integer.valueOf(uid));
        logger.info("ID为"+id+"解析的结果为："+standAddress);
        return standAddress;
    }

}
