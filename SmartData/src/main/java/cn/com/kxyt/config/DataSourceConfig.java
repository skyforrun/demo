package cn.com.kxyt.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author zj
 * @Description: 数据源配置
 * @date 2020/11/1716:35
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DruidDataSource dataSource(@Value("${spring.datasource.username}") String username,
                                      @Value("${spring.datasource.password}") String password,
                                      @Value("${spring.datasource.url}") String url,
                                      @Value("${spring.datasource.driver-class-name}") String driverClassName) {
        return createDataSource(username, password, url, driverClassName);
    }

    @Bean
    public DruidDataSource dataSource2(@Value("${spring.datasource2.username}") String username,
                                       @Value("${spring.datasource2.password}") String password,
                                       @Value("${spring.datasource2.url}") String url,
                                       @Value("${spring.datasource2.driver-class-name}") String driverClassName) {
        return createDataSource(username, password, url, driverClassName);
    }

    @Bean
    public DruidDataSource dataSource3(@Value("${spring.datasource3.username}") String username,
                                       @Value("${spring.datasource3.password}") String password,
                                       @Value("${spring.datasource3.url}") String url,
                                       @Value("${spring.datasource3.driver-class-name}") String driverClassName) {
        return createDataSource(username, password, url, driverClassName);
    }

    private DruidDataSource createDataSource(String username, String password, String url, String driverClassName) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }
}
