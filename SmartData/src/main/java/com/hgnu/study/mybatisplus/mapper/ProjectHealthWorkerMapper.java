package com.hgnu.study.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hgnu.study.mybatisplus.entity.ProjectHealthWorker;
import com.hgnu.study.mybatisplus.entity.ProjectHealthWorkerDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectHealthWorkerMapper extends BaseMapper<ProjectHealthWorker> {

    List queryBind(List<ProjectHealthWorkerDto> dto);
}
