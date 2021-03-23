package com.hgnu.study.elasticsearch.repository;

import com.hgnu.study.elasticsearch.entity.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsCityRepository extends ElasticsearchRepository<City, Long> {
}
