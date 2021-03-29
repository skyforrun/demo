package com.hgnu.study.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hgnu.study.mybatisplus.entity.HelpRelation;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface HelpRelationMapper extends BaseMapper<HelpRelation> {

    List<HelpRelation> selectByCondition();

    List<HelpRelation> queryPages();
}
