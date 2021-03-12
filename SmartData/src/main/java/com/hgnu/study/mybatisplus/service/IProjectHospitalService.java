package com.hgnu.study.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgnu.study.mybatisplus.entity.ProjectHospital;
import com.hgnu.study.mybatisplus.entity.ProjectHospitalDto;

import java.util.List;

public interface IProjectHospitalService extends IService<ProjectHospital> {

    void projectBindHospital(Long projectId, List<ProjectHospitalDto> projectHospitalDto);
}
