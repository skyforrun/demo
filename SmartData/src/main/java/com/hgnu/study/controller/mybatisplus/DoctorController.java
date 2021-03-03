package com.hgnu.study.controller.mybatisplus;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hgnu.study.core.Result;
import com.hgnu.study.mybatisplus.entity.Doctor;
import com.hgnu.study.mybatisplus.entity.PageVo;
import com.hgnu.study.mybatisplus.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorMapper doctorMapper;

    @GetMapping("/{page}/{size}")
    public Result page(@PathVariable Long page,@PathVariable Long size){
        //当前页,每页个数
        IPage<Doctor> iPage = new Page(page,size);
        IPage<Doctor> doctorIPage = doctorMapper.selectPage(iPage, null);
        PageVo pageVo = new PageVo();
        pageVo.setCurrent(page);
        pageVo.setSize(size);
        pageVo.setTotal(doctorIPage.getTotal());
        pageVo.setResultInfo(doctorIPage.getRecords());
        return Result.success(pageVo);
    }
}
