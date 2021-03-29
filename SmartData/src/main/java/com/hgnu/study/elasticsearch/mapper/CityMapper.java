package com.hgnu.study.elasticsearch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hgnu.study.elasticsearch.entity.City;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CityMapper extends BaseMapper<City> {

    List<City> queryMap(Map<String, Object> params);
}
