package com.hgnu.study.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgnu.study.mybatisplus.entity.HelpRelation;

import java.util.List;

public interface HelpRelationService extends IService<HelpRelation> {

    List<HelpRelation> selectByCondition(Integer current,Integer size);
}
