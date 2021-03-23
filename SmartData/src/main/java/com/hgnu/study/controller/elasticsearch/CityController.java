package com.hgnu.study.controller.elasticsearch;

import com.hgnu.study.core.Result;
import com.hgnu.study.elasticsearch.service.CityService;
import com.hgnu.study.elasticsearch.entity.City;
import com.hgnu.study.elasticsearch.thread.CityServiceThread;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/city")
@Api(value = "mysql-->ES测试",tags = "elasticsearch api相关测试")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    @Qualifier("cachedThreadPool")
    private ExecutorService executorService;

    @Autowired
    private CityServiceThread cityServiceThread;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @GetMapping(value = "/queryWithRange")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pagesize",value="查多少条",defaultValue = "500"),
            @ApiImplicitParam(name="offset",value="那一页开始",defaultValue = "1")
    })
    public Result queryWithRange(Integer pagesize,Integer offset) throws ExecutionException, InterruptedException {
       /*
        List<City> cities = cityService.queryWithRange(pagesize,offset);
        if (cities==null){
            throw new TipException("dsad");
        }*/
        Future submit = executorService.submit(cityServiceThread);
        return Result.success(submit.get());
    }


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
    @GetMapping(value = "/pageingSearch")
    public Result pageingSearch(){
        Page<City> cities = cityService.pageingSearch(1, 10);
        return Result.success(cities);
    }

    @ApiOperation(value = "排序查询")
    @GetMapping(value = "/sortQueryq")
    @ResponseBody
    public Object sortQueryq() throws Exception {
        Iterable<City> cities = cityService.sortQueryq();
        return Result.success(cities);
    }

    @ApiOperation(value = "精准查询")
    @GetMapping(value = "/termQuery")
    public Result termQuery(){
        List<City> cities = cityService.termQuery();
        System.out.println(cities);
        return Result.success(cities);
    }

    @ApiOperation(value = "匹配查询")
    @GetMapping(value = "/matchQuery")
    public Result matchQuery(String name){
        List<City> cities = cityService.matchQuery();
        return Result.success(cities);
    }

    @ApiOperation(value = "范围查询")
    @GetMapping(value = "/rangeQuery")
    public Object rangeQuery() throws Exception {
        cityService.rangeQuery();
        return Result.success();
    }

    @ApiOperation(value = "布尔查询，暂不可用")
    @GetMapping(value = "/boolQuery")
    public Result boolQuery(){
        List<City> cities = cityService.boolQuery();
        return Result.success(cities);
    }

    @ApiOperation(value = "多条件范围查询，暂不可用")
    @GetMapping(value = "/sortQuery")
    public Result sortQuery(){
        List<City> cities = cityService.sortQuery();
        return Result.success(cities);
    }

    @ApiOperation(value = "模糊查询")
    @GetMapping(value = "/fuzzyQuery")
    public Result fuzzyQuery(){
        List<City> cities = cityService.fuzzyQuery();
        return Result.success(cities);
    }
}
