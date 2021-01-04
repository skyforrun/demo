package cn.com.kxyt.service.impl;

import cn.com.kxyt.entity.elasticsearch.City;
import cn.com.kxyt.entity.elasticsearch.CityExample;
import cn.com.kxyt.mapper3.CityMapper;
import cn.com.kxyt.repository.EsCityRepository;
import cn.com.kxyt.service.CityService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class CityServiceImpl implements CityService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private EsCityRepository esCityRepository;

    @Autowired
    private ElasticsearchRestTemplate cityRestTemplate;



    /**
     * 将数据库的数据导入到ES中
     * @return
     */
    @Override
    public int importAll() {
        CityExample cityExample = new CityExample();
        List<City> cities = cityMapper.selectByExample(cityExample);
        Iterable iterable = esCityRepository.saveAll(cities);
        Iterator<City> iterator = iterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    /**
     * 创建索引与字段映射
     */
    public void createIndex(){
        cityRestTemplate.createIndex(City.class);
        logger.info("创建索引成功");
        cityRestTemplate.putMapping(City.class);
        logger.info("字段映射成功");
    }


    /**
     * 删除索引
     */
    public void deleteIndex(){
        cityRestTemplate.deleteIndex(City.class);
        logger.info("删除索引成功");
    }

    /**
     * 往ES中增加数据
     */
    @Override
    public List<City> addDocument(){
        City City = esCityRepository.save(new City(4L, "wuhan","wh","s",45964313));
        logger.info("添加数据:" + City.toString());
        //批量添加
        List<City> list = new ArrayList<>();
        list.add(new City(2L, "wuchang", " wc", "ds", 36991465));
        list.add(new City(3L, "hankou", "hk", "das",456143));
        esCityRepository.saveAll(list);
        logger.info("批量添加数据");
        return list;
    }

    /**
     * 往ES中删除数据
     */
    @Override
    public void deleteDocument(Long id){
        //esCityRepository.delete(new City(2L, "wuchang", " wc", "ds", 36991465));

        esCityRepository.deleteById(id);

    }

    /**
     * 往ES中修改数据
     */
    @Override
    public City updateDocument(){
        City city = esCityRepository.save(new City(1L, "hankou", "hk", "das",456143));
        return city;
    }

    /**
     * 分页查询
     *
     * @param page
     * @param size
     */
    @Override
    public Page<City> pageingSearch(int page, int size) {
        Page<City> citys = esCityRepository.findAll(PageRequest.of(page, size));
        citys.forEach(x -> logger.info(x.toString()));
        return citys;
    }

    /**
     * 排序查询
     */
    @Override
    public Iterable<City> sortQueryq() {
        Iterable<City> citys = esCityRepository.findAll(Sort.by("population").descending());
        citys.forEach(x -> logger.info(x.toString()));
        return citys;
    }

    /**
     * term query 精准
     */
    @Override
    public List<City> termQuery() {
        //根据term query 再来进行分页查询 此方法默认查询第一页的前十条
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termQuery("countrycode", "wc")).build();
        //分页
        PageRequest of = PageRequest.of(1, 10);
        searchQuery.setPageable(of);
        SearchHits<City> pages = cityRestTemplate.search(searchQuery,City.class);
        List<City> searchList = pages.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return searchList;
        //pages.stream().sorted(Comparator.comparing(City::getPopulation)).forEach(x -> logger.info(x.toString()));
    }

    /**
     * match query  ES已经进行排序 其本质为term query
     */
    @Override
    public List<City> matchQuery(){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("name", "Rotterdam")).build();
        SearchHits<City> pages = cityRestTemplate.search(searchQuery,City.class);
        List<City> searchProductList = pages.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return searchProductList;
    }

    /**
     * range query
     */
    @Override
    public List<City> rangeQuery(){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.rangeQuery("population").gte(100000).lte(120000)).build();
        SearchHits<City> pages = cityRestTemplate.search(searchQuery,City.class);
        List<City> searchList = pages.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return searchList;
    }

    /**
     * bool query
     */
    @Override
    public List<City> boolQuery(){
        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder boolQuery = boolQueryBuilder.filter(QueryBuilders.termQuery("brand", "小米"))
                .should(QueryBuilders.rangeQuery("price").gte(3000).lte(7000));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = searchQuery.withFilter(boolQuery);

        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        SearchHits<City> pages = cityRestTemplate.search(nativeSearchQuery,City.class);
        List<City> searchList = pages.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return searchList;
        /*StreamSupport.stream(items.spliterator(),false)
                .sorted(Comparator.comparingDouble(Item::getPrice))
                .forEach(x->log.info(x.toString()));*/

    }

    /**
     *  sort query
     */
    @Override
    public List<City> sortQuery(){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("brand", "小米"))
                .withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC)).build();
        SearchHits<City> Citys = cityRestTemplate.search(searchQuery,City.class);
        Citys.forEach(x->logger.info(x.toString()));
        List<City> searchList = Citys.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return searchList;
    }

    /**
     * fuzzy query 模糊查询
     */
    @Override
    public List<City> fuzzyQuery(){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.fuzzyQuery("name", "Holland")).build();
        SearchHits<City> pages = cityRestTemplate.search(searchQuery,City.class);
        List<City> searchList = pages.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return searchList;
    }

    @Override
    @Transactional("dataSourceTransactionManager3")
    public List<City> queryWithRange(Map<String, Integer> map) {
        int pageindex = 1;
        int pagesize = 500;
        boolean lookup = true;
        List<City> allHistoryList = new ArrayList<>();
        List<City> historyList;
        Map<String, Integer> params = new HashMap<>();
        params.put("pagesize", pagesize);
        while (lookup) {
            params.put("offset", (pageindex-1)*pagesize);
            historyList = cityMapper.selectAllWithRange(params);
            pageindex++;
            allHistoryList.addAll(historyList);
            if (historyList.size() < 500) {
                lookup = false;
            }
        }
       return allHistoryList;
    }

}
