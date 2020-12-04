package cn.com.kxyt.service.impl;

import cn.com.kxyt.entity.City;
import cn.com.kxyt.entity.CityExample;
import cn.com.kxyt.mapper3.CityMapper;
import cn.com.kxyt.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private ElasticsearchRepository cityRepository;

    @Autowired
    private ElasticsearchRestTemplate cityRestTemplate;

    @Override
    public int importAll() {
        CityExample cityExample = new CityExample();
        List<City> cities = cityMapper.selectByExample(cityExample);
        Iterable iterable = cityRepository.saveAll(cities);
        Iterator<City> iterator = iterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }
}
