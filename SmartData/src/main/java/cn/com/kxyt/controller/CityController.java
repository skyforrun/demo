package cn.com.kxyt.controller;

import cn.com.kxyt.core.Result;
import cn.com.kxyt.entity.City;
import cn.com.kxyt.entity.CityExample;
import cn.com.kxyt.mapper3.CityMapper;
import cn.com.kxyt.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@Api(value = "mysql-->ES测试")
public class CityController {

    @Autowired
    private CityService cityService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @PostMapping(value = "/importAll")
    public Result das(){
        int i = cityService.importAll();
        return Result.success(i);
    }



}
