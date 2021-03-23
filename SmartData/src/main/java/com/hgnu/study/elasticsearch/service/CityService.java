package com.hgnu.study.elasticsearch.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgnu.study.elasticsearch.entity.City;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CityService extends IService<City> {

    int importAll();

    List<City> addDocument();

    void deleteDocument(Long id);

    City updateDocument();

    Page<City> pageingSearch(int page, int size);

    Iterable<City> sortQueryq();

    List<City> termQuery();

    List<City> matchQuery();

    List<City> rangeQuery();

    List<City> boolQuery();

    List<City> sortQuery();

    List<City> fuzzyQuery();

    List<City> queryWithRange(Integer pagesize,Integer offset);
}
