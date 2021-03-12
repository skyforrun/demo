package com.hgnu.study.Lok;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @Author zj
 * @Description redisson配置文件
 * @Date 2021/3/5 15:17
 */
@Configuration
public class RedissonConfig {
    @Autowired
    private Environment env;

    //@Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws IOException {
        return Redisson.create(sonConfig());
    }

    @Bean
    public Config sonConfig() throws IOException {
        String[] profiles = env.getActiveProfiles();
        String profile = "";
        if (profiles.length > 0) {
            profile = "-" + profiles[0];
        }
        Config config = Config.fromYAML(new ClassPathResource("redisson" + profile + ".yml").getInputStream());
        return config;
    }
}
