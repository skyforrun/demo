package com.hgnu.study.thread;

import com.hgnu.study.entity.elasticsearch.City;
import com.hgnu.study.service.CityService;
import com.hgnu.study.service.impl.CityServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author zj
 * @Description: 线程池执行批量查询
 * @date 2021/1/12 19:13
 */

@Component
public class CityServiceThread implements Callable {

    @Autowired
    CityService cityService;

    @Override
    public List<City> call() {
        List<City> cities = cityService.queryWithRange(500, 1);
        return cities;
    }
}
