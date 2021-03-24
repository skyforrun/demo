package com.hgnu.study.config.mybatis;

/**
 * @author zj
 * @Description: MybatisConfig配置类
 * @date 2020/11/1716:32
 */

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.hgnu.study.mybatisplus.config.PageConfig;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;


public class MybatisConfig {

    @Configuration
    @MapperScan(basePackages = "com.hgnu.study.mapper",sqlSessionTemplateRef = "sqlSessionTemplate")
    public static class Db1 {

        @Bean
        @Primary
        public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(dataSource);
            return factoryBean.getObject();
        }

        @Bean
        @Primary
        public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
            return new SqlSessionTemplate(sqlSessionFactory);
        }

        @Bean
        @Primary
        public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }

    @Configuration
    @MapperScan(basePackages = "com.hgnu.study.mybatisplus.mapper",sqlSessionTemplateRef = "sqlSessionTemplate2")
    public static class Db2 {

        @Autowired
        PageConfig pageConfig;

        @Bean
        public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2") DataSource dataSource) throws Exception {
            MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
            factoryBean.setDataSource(dataSource);
            //mybatis-plus配置分页
            factoryBean.setPlugins(new Interceptor[]{pageConfig.mybatisPlusInterceptor()});
            return factoryBean.getObject();
        }

        @Bean
        public SqlSessionTemplate sqlSessionTemplate2(@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory) throws Exception {
            return new SqlSessionTemplate(sqlSessionFactory);
        }

        @Bean
        public DataSourceTransactionManager dataSourceTransactionManager2(@Qualifier("dataSource2") DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }

    @Configuration
    @MapperScan(basePackages = "com.hgnu.study.elasticsearch.mapper",sqlSessionTemplateRef = "sqlSessionTemplate3")
    public static class Db3 {

        @Autowired
        PageConfig pageConfig;

        @Bean
        public SqlSessionFactory sqlSessionFactory3(@Qualifier("dataSource3") DataSource dataSource) throws Exception {
            MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
            factoryBean.setDataSource(dataSource);
            //mybatis-plus配置分页
            factoryBean.setPlugins(new Interceptor[]{pageConfig.mybatisPlusInterceptor()});
            return factoryBean.getObject();
        }

        @Bean
        public SqlSessionTemplate sqlSessionTemplate3(@Qualifier("sqlSessionFactory3") SqlSessionFactory sqlSessionFactory) throws Exception {
            return new SqlSessionTemplate(sqlSessionFactory);
        }

        @Bean
        public DataSourceTransactionManager dataSourceTransactionManager3(@Qualifier("dataSource3") DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }
}
