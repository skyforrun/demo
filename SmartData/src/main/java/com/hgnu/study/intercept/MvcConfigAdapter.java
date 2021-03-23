package com.hgnu.study.intercept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zj
 * @Description: 配置文件资源映射
 * @date 2020/11/17 11:28
 */
@Configuration
public class MvcConfigAdapter implements WebMvcConfigurer {

    @Autowired
    ExceptionHandleInterceptor exceptionHandleInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 浏览器地址栏输入/
         * 即可访问qrcode文件夹
         */
        registry.addResourceHandler("/**").addResourceLocations("file:./public/qrcode/");

        /**
         * swagger2报404
         */
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        //springboot访问静态资源
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/resources/").addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/").addResourceLocations("classpath:/templates/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(exceptionHandleInterceptor).addPathPatterns("/**").excludePathPatterns("/demo/**");

    }
}
