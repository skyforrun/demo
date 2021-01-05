package com.hgnu.study.repository;

import com.hgnu.study.entity.elasticsearch.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsCityRepository extends ElasticsearchRepository<City, Long> {
}
