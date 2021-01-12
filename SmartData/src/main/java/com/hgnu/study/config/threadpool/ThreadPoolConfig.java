package com.hgnu.study.config.threadpool;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author zj
 * @Description: 线程池配置
 * @date 2021/1/12 19:03
 */
@Configuration
public class ThreadPoolConfig {

    /**
     * 固定线程池数量
     * @return
     */
    @Bean
    public ExecutorService fixedThreadPool(){
        return Executors.newFixedThreadPool(8);
    }

    /**
     * 无核心线程线程池
     * @return
     */
    @Bean
    public ExecutorService cachedThreadPool(){
        return Executors.newCachedThreadPool();
    }

    /**
     * 单线程
     * @return
     */
    @Bean
    public ExecutorService singleThreadPool(){
        return Executors.newSingleThreadExecutor();
    }
}
