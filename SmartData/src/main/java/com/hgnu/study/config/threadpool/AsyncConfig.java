package com.hgnu.study.config.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.*;

/**
 * @Author zj
 * @Description 异步处理任务配置
 * @Date 2021/3/26 14:45
 */


@Configuration
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

    @Override
    @Bean(name = "taskExcutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(8);
        executor.setKeepAliveSeconds(0);
        executor.setThreadNamePrefix("batchThread-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.error("异常信息:{}",throwable.getMessage());
            log.error("方法名:{}",method.getName());
            for (Object object : objects) {
                log.info("参数值:{}",object);
            }
        };
    }
}
