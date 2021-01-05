package com.hgnu.study.service;

import com.hgnu.study.entity.elasticsearch.City;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CityService {

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
