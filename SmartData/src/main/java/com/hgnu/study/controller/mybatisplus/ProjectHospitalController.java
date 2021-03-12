package com.hgnu.study.controller.mybatisplus;

import com.hgnu.study.core.Result;
import com.hgnu.study.exception.TipException;
import com.hgnu.study.mybatisplus.entity.ProjectHospitalDto;
import com.hgnu.study.mybatisplus.service.IProjectHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * @Author zj
 * @Description
 * @Date 2021/3/12 15:47
 */

@RestController
@RequestMapping("/project")
public class ProjectHospitalController{

    @Autowired
    IProjectHospitalService projectHospitalService;

    @PostMapping("/projectBindHospital/{projectId}")

    public Result projectBindHospital(@PathVariable("projectId") Long projectId, @RequestBody List<ProjectHospitalDto> projectHospitalDto) {
        projectHospitalService.projectBindHospital(projectId,projectHospitalDto);
        return Result.success();
    }
}
