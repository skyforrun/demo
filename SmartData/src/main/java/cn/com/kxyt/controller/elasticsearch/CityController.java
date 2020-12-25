package cn.com.kxyt.controller.elasticsearch;

import cn.com.kxyt.core.Result;
import cn.com.kxyt.entity.elasticsearch.City;
import cn.com.kxyt.exception.GlobalExceptionHandler;
import cn.com.kxyt.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/city")
@Api(value = "mysql-->ES测试",tags = "elasticsearch api相关测试")
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
    @GetMapping(value = "/pageingSearch")
    public Result pageingSearch(){
        Page<City> cities = cityService.pageingSearch(1, 10);
        return Result.success(cities);
    }

    @ApiOperation(value = "排序查询")
    @GetMapping(value = "/sortQueryq")
    @ResponseBody
    public Object sortQueryq(HttpServletRequest request) throws Exception {
        Iterable<City> cities;
        try {
            int i = 6/0;
            cities = cityService.sortQueryq();
        }catch (Exception e){
            return GlobalExceptionHandler.handleException(e,request,"发生异常了");
        }

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
    public Object rangeQuery(HttpServletRequest request) throws Exception {
        try {
            int i = 5/0;
            cityService.rangeQuery();
        }catch (Exception e){
            return GlobalExceptionHandler.handleException(e,request,"youyihcang");
        }
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
