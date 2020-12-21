package cn.com.kxyt.repository;

import cn.com.kxyt.entity.elasticsearch.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsCityRepository extends ElasticsearchRepository<City, Long> {
}
