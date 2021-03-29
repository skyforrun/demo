package com.hgnu.study.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgnu.study.mybatisplus.entity.HelpRelation;

import java.util.List;
import java.util.concurrent.Future;

public interface HelpRelationService extends IService<HelpRelation> {

    Future<List<HelpRelation>> selectByCondition(Integer current, Integer size);
}
