package cn.com.kxyt.config;

/**
 * @author zj
 * @Description: MybatisConfig配置类
 * @date 2020/11/1716:32
 */

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;
import java.util.Properties;

public class MybatisConfig {

    @Configuration
    @MapperScan(basePackages = "cn.com.kxyt.mapper",sqlSessionTemplateRef = "sqlSessionTemplate")
    public static class Db1 {

        @Bean
        @Primary
        public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

            //配置分页插件，详情请查阅官方文档
            PageHelper pageHelper = new PageHelper();
            Properties properties = new Properties();
            properties.setProperty("pageSizeZero", "true");//分页尺寸为0时查询所有纪录不再执行分页
            properties.setProperty("reasonable", "true");//页码<=0 查询第一页，页码>=总页数查询最后一页
            properties.setProperty("supportMethodsArguments", "true");//支持通过 Mapper 接口参数来传递分页参数
            pageHelper.setProperties(properties);

            //添加插件
            factoryBean.setPlugins(new Interceptor[]{pageHelper});
            factoryBean.setDataSource(dataSource);
            //factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath:cn.com.kxyt/mapper/"));
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
    @MapperScan(basePackages = "cn.com.kxyt.mapper2", sqlSessionTemplateRef = "sqlSessionTemplate2")
    public static class Db2 {

        @Bean
        public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2") DataSource dataSource) throws Exception {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            //配置分页插件，详情请查阅官方文档
            PageHelper pageHelper = new PageHelper();
            Properties properties = new Properties();
            properties.setProperty("pageSizeZero", "true");//分页尺寸为0时查询所有纪录不再执行分页
            properties.setProperty("reasonable", "true");//页码<=0 查询第一页，页码>=总页数查询最后一页
            properties.setProperty("supportMethodsArguments", "true");//支持通过 Mapper 接口参数来传递分页参数
            pageHelper.setProperties(properties);

            //添加插件
            factoryBean.setPlugins(new Interceptor[]{pageHelper});
            factoryBean.setDataSource(dataSource);
            //factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath:cn.com.kxyt/mapper2/*.xml"));
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
}
