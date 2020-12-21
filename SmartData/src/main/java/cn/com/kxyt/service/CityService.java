package cn.com.kxyt.service;

import cn.com.kxyt.entity.elasticsearch.City;
import org.springframework.data.domain.Page;

import java.util.List;

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
}
