package com.hgnu.study.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgnu.study.mybatisplus.entity.ProjectHealthWorker;
import com.hgnu.study.mybatisplus.entity.ProjectHealthWorkerDto;
import java.util.List;

public interface IProjectHealthWorkerService extends IService<ProjectHealthWorker> {

    void projectBindHealthWorker(Long projectId, List<ProjectHealthWorkerDto> projectHealthWorkerDtos);

    void projectUnsetHealthWorker(List<ProjectHealthWorkerDto> projectHealthWorkerDtos);

    List queryBind(List<ProjectHealthWorkerDto> projectHealthWorkerDtos);
}
