package com.hgnu.study.controller.mybatisplus;

import com.hgnu.study.core.Result;
import com.hgnu.study.mybatisplus.entity.ProjectHealthWorkerDto;
import com.hgnu.study.mybatisplus.service.IProjectHealthWorkerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zj
 * @Description
 * @Date 2021/3/15 14:23
 */

@RestController
@RequestMapping("/project")
@Api(value = "项目医护工作者绑定前端控制器",tags = "对项目医护工作者进行绑定和解绑工作")
public class ProjectHealthWorkerController {

    @Autowired
    IProjectHealthWorkerService projectHealthWorkerService;

    @PostMapping("/projectBindHealthWorker/{projectId}")
    @ApiOperation(value="项目医护工作者绑定/更新绑定",notes="有id为更新绑定,无id为增加绑定")
  /*  @ApiImplicitParams({
            @ApiImplicitParam(name="projectId",value="项目id"),
            @ApiImplicitParam(name="projectHospitalDto",value="项目医院绑定")
    })*/
    public Result projectBindHospital(@PathVariable("projectId") Long projectId, @RequestBody List<ProjectHealthWorkerDto> projectHealthWorkerDtos) {
        projectHealthWorkerService.projectBindHealthWorker(projectId,projectHealthWorkerDtos);
        return Result.success();
    }

    @PostMapping("/projectUnsetHealthWorker")
    @ApiOperation(value="项目医护工作者解绑",notes="必须有项目医护工作者的id")
  /*  @ApiImplicitParams({
            @ApiImplicitParam(name="projectHospitalDto",value="项目医院绑定")
    })*/
    public Result projectUnsetHospital(@RequestBody List<ProjectHealthWorkerDto> projectHealthWorkerDtos) {
        projectHealthWorkerService.projectUnsetHealthWorker(projectHealthWorkerDtos);
        return Result.success();
    }

    @PostMapping("/queryBind")
    @ApiOperation(value="查询项目医护工作者绑定")
    public Result queryBind(@RequestBody List<ProjectHealthWorkerDto> projectHealthWorkerDtos){
        List list = projectHealthWorkerService.queryBind(projectHealthWorkerDtos);
        return Result.success(list);
    }
}
