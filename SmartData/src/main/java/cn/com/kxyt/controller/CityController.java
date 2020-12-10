package cn.com.kxyt.controller;

import cn.com.kxyt.core.Result;
import cn.com.kxyt.entity.City;
import cn.com.kxyt.entity.CityExample;
import cn.com.kxyt.mapper3.CityMapper;
import cn.com.kxyt.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Result importAll(){
        int i = cityService.importAll();
        return Result.success(i);
    }

    @ApiOperation(value = "导入一条数据到ES中")
    @PostMapping(value = "/addCity")
    public Result addCity(){
        List<City> cities = cityService.addDocument();
        return Result.success(cities);
    }

    @ApiOperation(value = "删除一条记录")
    @PostMapping(value = "/deleteCity")
    public Result deleteCity(){
        cityService.deleteDocument(2L);
        return Result.success();
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/pageingSearch")
    public Result pageingSearch(){
        Page<City> cities = cityService.pageingSearch(1, 10);
        return Result.success(cities);
    }

    @ApiOperation(value = "排序查询")
    @PostMapping(value = "/sortQueryq")
    public Result sortQueryq(){
        Iterable<City> cities = cityService.sortQueryq();
        return Result.success(cities);
    }

    @ApiOperation(value = "精准查询")
    @PostMapping(value = "/termQuery")
    public Result termQuery(){
        List<City> cities = cityService.termQuery();
        return Result.success(cities);
    }

    @ApiOperation(value = "匹配查询")
    @PostMapping(value = "/matchQuery")
    public Result matchQuery(){
        List<City> cities = cityService.matchQuery();
        return Result.success(cities);
    }

    @ApiOperation(value = "范围查询")
    @PostMapping(value = "/rangeQuery")
    public Result rangeQuery(){
        List<City> cities = cityService.rangeQuery();
        return Result.success(cities);
    }

    @ApiOperation(value = "布尔查询，暂不可用")
    @PostMapping(value = "/boolQuery")
    public Result boolQuery(){
        List<City> cities = cityService.boolQuery();
        return Result.success(cities);
    }

    @ApiOperation(value = "多条件范围查询，暂不可用")
    @PostMapping(value = "/sortQuery")
    public Result sortQuery(){
        List<City> cities = cityService.sortQuery();
        return Result.success(cities);
    }

    @ApiOperation(value = "模糊查询")
    @PostMapping(value = "/fuzzyQuery")
    public Result fuzzyQuery(){
        List<City> cities = cityService.fuzzyQuery();
        return Result.success(cities);
    }
}
